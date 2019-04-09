package com.zy.dailyServiceTest.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;

/**
 *     controller 拦截器配置
 * @Author wangwentao
 * @Description 
 * @Date 2019/3/28 19:46
 **/
public class ControllerInterceptorConfig implements HandlerInterceptor {

    private Semaphore sh = new Semaphore(3);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("进入自定义拦截器 前置  ----->  开始");

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null ;

        System.out.println("当前队列的长度是 ： " + sh.availablePermits() );

        if(sh.tryAcquire()){
            System.out.println("进入自定义拦截器 前置 ----->  结束");
            return true;
        }else {
            ObjectMapper om = new ObjectMapper();
            Map map = new HashMap();
            map.put("code","11");
            map.put("msg","超出最大限度1000 被拦截");
            String str = om.writeValueAsString(map);
            out = response.getWriter();
            out.append(str);
            System.out.println("进入自定义拦截器 前置 ----->  被拦截");
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        System.out.println("进入自定义拦截器 后置 ----->  结束");
        sh.release();
        System.out.println("进入自定义拦截器 后置 ----->  结束");
    }
}
