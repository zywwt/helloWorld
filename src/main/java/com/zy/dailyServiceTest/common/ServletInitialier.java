package com.zy.dailyServiceTest.common;

import com.zy.dailyServiceTest.DailyServiceTestApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 *  配置springboot出事换
 * @Author wangwentao
 * @Description 
 * @Date 2019/4/8 10:55
 **/
public class ServletInitialier extends SpringBootServletInitializer{
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(DailyServiceTestApplication.class);
    }
}
