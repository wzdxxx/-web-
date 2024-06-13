package com.pro.dao;


import com.pro.entity.Doctor;
import com.pro.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
public interface UserDao extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
    @Query(value = "select * from doctor where user.tel like %?1%", nativeQuery = true)
    public List<User> findByTel(String tel);
    @Query(value = "select * from doctor where user.sex like %?1%", nativeQuery = true)
    public List<User> findBySex(String sex);
    //
    @Query(value = "delete from user where user.tel like %?1%", nativeQuery = true)
    public List<User> deleteByTel(String tel);

}