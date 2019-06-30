package com.bob.apigateway.filter;

import com.bob.apigateway.utils.CookieUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
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
 * @Auther: toudaizhi
 * @Date: 2019-06-03 10:10
 * @Description:
 */
@Component
public class AuthorityBuyerFilter extends ZuulFilter {

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
        //获取上下文
        RequestContext requestContext = RequestContext.getCurrentContext();
        //获取
        HttpServletRequest request = requestContext.getRequest();

        /**
         * /order/create 只能买家访问（买家openid）
         */

        //是否开启
        if("/app-bob-order/order/create".equals(request.getRequestURI())){
            return true;
        }
        return false;

    }

    @Override
    public Object run() throws ZuulException {

        //获取上下文
        RequestContext requestContext = RequestContext.getCurrentContext();
        //获取request
        HttpServletRequest request = requestContext.getRequest();

         Cookie cookie = CookieUtil.getCookie(request, "openid");
         if(cookie == null || StringUtils.isEmpty(cookie.getValue())){
             requestContext.setSendZuulResponse(false);
             requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
         }

        return null;
    }
}
