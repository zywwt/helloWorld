package com.zy.dailyServiceTest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Semaphore 工具类 限流测试
 *
 * @Author wangwentao
 * @Description 
 * @Date 2019/3/28 19:34
 **/
@Controller
public class CurrentLimitingController {

    @RequestMapping(value = "/limit",method = RequestMethod.POST)
    @ResponseBody
    public Map currentLimit(@RequestParam("name") String name ) {
        System.out.println("进入controller请求 --> 开始");
        System.out.println("当前请求的的名称是 " + name);

        try {
            // 休眠 10 秒钟
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Map map = new HashMap();
        map.put("name",name);
        map.put("code","00");
        map.put("msg","正常请求进入");
        System.out.println("进入controller请求 --> 结束");
        return map;
    }

}
