package com.bookcode.dao;

import com.bookcode.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserCrudRepository extends CrudRepository<User,Long> {
    List<User> findByLastName(String lastName);
}
