package com.imooc.getway;

import com.imooc.getway.constant.RedisConstant;
import com.imooc.getway.utils.CookieUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * Create by Jack SD
 * Date 2019/9/29 0029 16:48
 */
@Component
@Slf4j
public class SellerFilter extends ZuulFilter {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return PRE_DECORATION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        if ("/order/order/finish".equals(request.getRequestURI())) {
            return true;
        }
        return false;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();

        /**
         * order/create 只能买家访问（cookie有openid）
         * order/finish 只能卖家访问（cookie有token，在redis查到值）
         * product/list 都能访问
         */

        log.info("/order/finish");
        Cookie cookie = CookieUtil.get(request, "token");
        if (cookie == null || StringUtils.isEmpty(cookie.getValue()) ||
            StringUtils.isEmpty(stringRedisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_TEMPLATE, cookie.getValue())))) {
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        }

//            String redis = stringRedisTemplate.opsForValue().get(String.format("token_%s", cookie.getValue()));
//            if (StringUtils.isEmpty(redis)) {
//                requestContext.setSendZuulResponse(false);
//                requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
//            }

        return null;
    }
}
