package com.bookcode.service;

public interface AccountService {
    //从A用户转账至b用户，金额为account
    public void transferAccounts (int fromUser, int toUser, float account) ;
}
