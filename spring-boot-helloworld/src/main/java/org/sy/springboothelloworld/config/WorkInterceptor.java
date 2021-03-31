package org.sy.springboothelloworld.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author john
 * @Date 2021/1/28 23:39
 * @Version 1.0
 */
@Slf4j
@Component
public class WorkInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        log.info("work dump");
        return true;
    }

}
