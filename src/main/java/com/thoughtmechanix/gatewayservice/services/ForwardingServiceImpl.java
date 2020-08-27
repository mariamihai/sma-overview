package com.thoughtmechanix.gatewayservice.services;

import com.netflix.zuul.context.RequestContext;
import com.thoughtmechanix.gatewayservice.model.AbTestingRouteDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicHttpRequest;
import org.springframework.cloud.netflix.zuul.filters.ProxyRequestHelper;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
@Slf4j
@RequiredArgsConstructor
public class ForwardingServiceImpl implements ForwardingService {


    private final RestTemplate restTemplate;
    private ProxyRequestHelper helper = new ProxyRequestHelper();


    @Override
    public AbTestingRouteDto getAbRoutingInfo(String serviceName) {
        ResponseEntity<AbTestingRouteDto> restExchange;

        try {
            restExchange = restTemplate.exchange(
                    "http://special-routes-service/v1/route/abtesting/{serviceName}",
                    HttpMethod.GET, null, AbTestingRouteDto.class, serviceName);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) return null;
            throw e;
        }

        return restExchange.getBody();
    }

    @Override
    public boolean useSpecialRoute(AbTestingRouteDto testRoute) {
        Random random = new Random();

        if (testRoute.getActive().equals("N")) return false;

        int value = random.nextInt(11);

        return testRoute.getWeight() < value;
    }

    @Override
    public String buildRouteString(String requestURI, String newEndpoint) {
        return newEndpoint + requestURI;
    }

    @Override
    public void forwardToSpecialRoute(String route) {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();

        MultiValueMap<String, String> headers = this.helper.buildZuulRequestHeaders(request);
        MultiValueMap<String, String> params = this.helper.buildZuulRequestQueryParams(request);

        String verb = getVerb(request);
        InputStream requestEntity = getRequestBody(request);

        if (request.getContentLength() < 0) {
            context.setChunkedRequestBody();
        }

        this.helper.addIgnoredHeaders();

        HttpResponse response;
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            response = forward(httpClient, verb, route, request, headers,
                    params, requestEntity);
            setResponse(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getVerb(HttpServletRequest request) {
        return request.getMethod().toUpperCase();
    }

    private InputStream getRequestBody(HttpServletRequest request) {
        InputStream requestEntity = null;
        try {
            requestEntity = request.getInputStream();
        } catch (IOException ex) {
            // no requestBody is ok.
        }
        return requestEntity;
    }

    private void setResponse(HttpResponse response) throws IOException {
        this.helper.setResponse(response.getStatusLine().getStatusCode(),
                response.getEntity() == null ? null : response.getEntity().getContent(),
                revertHeaders(response.getAllHeaders()));
    }

    private HttpResponse forward(HttpClient httpclient, String verb, String uri,
                                 HttpServletRequest request, MultiValueMap<String, String> headers,
                                 MultiValueMap<String, String> params, InputStream requestEntity) throws Exception {
        URL host = new URL(uri);
        HttpHost httpHost = getHttpHost(host);

        setRouteHost(httpHost);

        HttpRequest httpRequest;
        int contentLength = request.getContentLength();
        InputStreamEntity entity = new InputStreamEntity(requestEntity, contentLength,
                request.getContentType() != null ? ContentType.create(request.getContentType()) : null);

        switch (verb.toUpperCase()) {
            case "POST":
                HttpPost httpPost = new HttpPost(uri);
                httpRequest = httpPost;
                httpPost.setEntity(entity);
                break;
            case "PUT":
                HttpPut httpPut = new HttpPut(uri);
                httpRequest = httpPut;
                httpPut.setEntity(entity);
                break;
            case "PATCH":
                HttpPatch httpPatch = new HttpPatch(uri );
                httpRequest = httpPatch;
                httpPatch.setEntity(entity);
                break;
            default:
                httpRequest = new BasicHttpRequest(verb, uri);

        }
        try {
            httpRequest.setHeaders(convertHeaders(headers));

            return forwardRequest(httpclient, httpHost, httpRequest);
        } finally { }
    }

    private void setRouteHost(HttpHost httpHost) throws Exception {
        RequestContext.getCurrentContext().setRouteHost(new URL(httpHost.toString()));
    }

    private HttpHost getHttpHost(URL host) {
        return new HttpHost(host.getHost(), host.getPort(), host.getProtocol());
    }

    private Header[] convertHeaders(MultiValueMap<String, String> headers) {
        List<Header> list = new ArrayList<>();

        for (String name : headers.keySet()) {
            for (String value : headers.get(name)) {
                list.add(new BasicHeader(name, value));
            }
        }
        return list.toArray(new BasicHeader[0]);
    }

    private HttpResponse forwardRequest(HttpClient httpclient, HttpHost httpHost,
                                        HttpRequest httpRequest) throws IOException {
        return httpclient.execute(httpHost, httpRequest);
    }

    private MultiValueMap<String, String> revertHeaders(Header[] headers) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        for (Header header : headers) {
            String name = header.getName();
            if (!map.containsKey(name)) {
                map.put(name, new ArrayList<>());
            }
            map.get(name).add(header.getValue());
        }
        return map;
    }

}
