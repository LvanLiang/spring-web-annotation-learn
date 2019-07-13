package com.liang.service;

import org.springframework.stereotype.Service;

/**
 * @author: Liangxp
 * @date: 2019/7/13 16:04
 */
@Service
public class UserService {
    public String insert(String name) {
        return "hello..." + name;
    }
}
