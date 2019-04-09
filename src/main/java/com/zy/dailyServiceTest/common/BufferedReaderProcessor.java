package com.zy.dailyServiceTest.common;

import java.io.BufferedReader;
import java.io.IOException;

/**
 *  lambda 表达式
 *  java8 注解 @FunctionalInterface 标注与函数式接口上
 *  函数式接口  接口只定义了一个抽象方法
 */
@FunctionalInterface
public interface BufferedReaderProcessor {
    public String process(BufferedReader br) throws IOException;
}
