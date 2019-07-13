package com.liang.controller;

import com.liang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author: Liangxp
 * @date: 2019/7/13 15:16
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping("/insert")
    public String insert(String name){
        return userService.insert(name);
    }

    @RequestMapping("/list")
    public String list(){
        return "success";
    }
}
