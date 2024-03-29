package com.liang.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * Spring的容器不扫描controller;父容器
 * @author: Liangxp
 * @date: 2019/7/13 14:53
 */
@ComponentScan(value="com.liang",excludeFilters={
        @ComponentScan.Filter(type=FilterType.ANNOTATION,classes={Controller.class})
})
public class RootConfig {

}
