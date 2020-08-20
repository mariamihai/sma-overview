package com.thoughtmechanix.gatewayservice.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TrackingFilter extends ZuulFilter {

    private static final int FILTER_ORDER = 1;
    private static final boolean SHOULD_BE_ACTIVE = true;

    @Override
    public String filterType() {
        return FilterUtils.PRE_FILTER_TYPE;
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
        log.debug("In pre-filter.");

        // Play with the request
//        RequestContext ctx = RequestContext.getCurrentContext();
//        log.debug(ctx.toString());
//        log.debug(ctx.getRequest().toString());
//        log.debug(ctx.getZuulRequestHeaders().toString());

        return null;
    }
}
