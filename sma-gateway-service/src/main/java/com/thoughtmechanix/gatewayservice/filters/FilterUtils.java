package com.thoughtmechanix.gatewayservice.filters;

import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

@Component
public class FilterUtils {

    public static final String PRE_FILTER_TYPE = "pre";
    public static final String ROUTE_FILTER_TYPE = "route";
    public static final String POST_FILTER_TYPE = "post";


    public String getServiceId(){
        RequestContext ctx = RequestContext.getCurrentContext();

        //We might not have a service id if we are using a static, non-eureka route.
        if (ctx.get("serviceId")==null) return "";
        return ctx.get("serviceId").toString();
    }
}
