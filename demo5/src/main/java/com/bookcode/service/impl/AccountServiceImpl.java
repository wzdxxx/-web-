package com.bookcode.service.impl;


import javax.annotation.Resource;
import javax.transaction.Transactional;
import com.bookcode.dao.AccountDao;
import com.bookcode.entity.Account;
import com.bookcode.service.AccountService;
import org.springframework.stereotype.Service;
@Service ("accountService")
public class AccountServiceImpl implements AccountService {
    @Resource
    private AccountDao accountDao;
    @Transactional
    public void transferAccounts (int fromUser, int toUser, float account) {
        Account fromAccount=accountDao.getOne(fromUser) ;
        fromAccount.setBalance (fromAccount.getBalance()-account) ;
        accountDao.save(fromAccount) ;
        Account toAccount=accountDao.getOne(toUser) ;
        toAccount.setBalance (toAccount.getBalance() + account) ;
        //int zero=1/0;
        accountDao.save(toAccount);
    }
}

