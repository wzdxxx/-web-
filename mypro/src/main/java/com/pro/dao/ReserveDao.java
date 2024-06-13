package com.pro.dao;

import com.pro.entity.Reserve;
import com.pro.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
public interface ReserveDao extends JpaRepository<Reserve, Integer>, JpaSpecificationExecutor<Reserve> {
    @Query(value = "select * from reserve where reserve.tel like %?1%", nativeQuery = true)
    public List<Reserve> findByTel(String tel);
    @Query(value = "delete from reserve where reserve.tel like %?1%", nativeQuery = true)
    public List<User> deleteByTel(String tel);

}
