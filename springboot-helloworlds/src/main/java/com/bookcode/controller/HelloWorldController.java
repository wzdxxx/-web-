package com.bookcode.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    @Value("${helloWorld}")
    private String hello;
    @Value("${mysql.jdbcName}")
    private String jdbcName;
    @Value("${mysql.dbUrl}")
    private String dbUrl;
    @Value("${mysql.userName}")
    private String userName;
    @Value("${mysql.password}")
    private String password;
    @RequestMapping("/hello")
    public String hello(){
        return "Hello World";
    }
    @RequestMapping("/showJdbc")
    public String showJdbc(){
        return "mysql.jdbcName: " + jdbcName + ", dbUrl: " + dbUrl + ", userName: " + userName + ", password: " + password;
    }
}
