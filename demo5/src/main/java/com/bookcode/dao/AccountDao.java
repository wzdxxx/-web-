package com.bookcode.dao;


import com.bookcode.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
public interface AccountDao extends JpaRepository<Account, Integer> {

}
