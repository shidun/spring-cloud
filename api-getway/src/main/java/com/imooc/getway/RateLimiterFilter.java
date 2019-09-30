package com.imooc.getway;

import com.google.common.util.concurrent.RateLimiter;
import com.imooc.getway.exception.GetWayException;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.*;
/**
 * Create by Jack SD
 * Date 2019/9/29 0029 17:14
 */
@Component
@Slf4j
public class RateLimiterFilter extends ZuulFilter {
    private static final RateLimiter RATE_LIMITER = RateLimiter.create(100);

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return SERVLET_DETECTION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        if (!RATE_LIMITER.tryAcquire()) {
            throw new GetWayException();
        }
        return null;
    }
}
