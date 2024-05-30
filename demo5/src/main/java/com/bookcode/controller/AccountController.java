package com.bookcode.controller;


import javax.annotation.Resource;
import com.bookcode.service.AccountService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Resource
    private AccountService accoutService;
    @RequestMapping("/transfer")
    public String transferAccount () {
        try{
            accoutService. transferAccounts(1, 2, 200) ;
            return "OK";
        }
        catch (Exception e) {
            return "NO";
        }
    }
}

