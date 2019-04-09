package com.zy.dailyServiceTest.common;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 *
 * @Author wangwentao
 * @Description 
 * @Date 2019/3/28 20:10
 **/
@Component
public class WebAppConfig extends WebMvcConfigurationSupport {
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new ControllerInterceptorConfig()).addPathPatterns("/**");
    }
}
