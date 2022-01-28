package com.thoughtmechanix.gatewayservice.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.thoughtmechanix.gatewayservice.model.AbTestingRouteDto;
import com.thoughtmechanix.gatewayservice.services.ForwardingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class SpecialRoutesFilter extends ZuulFilter {

    private static final int FILTER_ORDER =  1;
    private static final boolean SHOULD_BE_ACTIVE =true;

    private final FilterUtils filterUtils;
    private final ForwardingService forwardingService;


    @Override
    public String filterType() {
        return FilterUtils.ROUTE_FILTER_TYPE;
    }

    @Override
    public int filterOrder() {
        return FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return SHOULD_BE_ACTIVE;
    }

    @Override
    public Object run() {
        log.debug("In route filter.");

        RequestContext ctx = RequestContext.getCurrentContext();
        AbTestingRouteDto abTestRoute = forwardingService.getAbRoutingInfo(filterUtils.getServiceId());

        if (shouldForward(abTestRoute)) {
            String route = forwardingService.buildRouteString(ctx.get("requestURI").toString(),
                                                              abTestRoute.getEndpoint());

            log.debug("Forwarding " + filterUtils.getServiceId() + " to " + route);

            forwardingService.forwardToSpecialRoute(route);
        }

        return null;
    }

    private boolean shouldForward(AbTestingRouteDto abTestRoute) {
        return abTestRoute != null && forwardingService.useSpecialRoute(abTestRoute);
    }
}

