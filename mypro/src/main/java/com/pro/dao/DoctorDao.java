package com.pro.dao;


import com.pro.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
public interface DoctorDao extends JpaRepository<Doctor, Integer>, JpaSpecificationExecutor<Doctor> {
    @Query(value = "select * from doctor where doctor.name like %?1%", nativeQuery = true)
    public List<Doctor> findByName(String name);
    @Query(value = "select * from doctor where doctor.time like %?1%", nativeQuery = true)
    public List<Doctor> findByTime(String time);
    @Query(value = "select * from doctor where doctor.department like %?1%", nativeQuery = true)
    public List<Doctor> findByDepartment(String department);
    @Query(value = "select * from doctor where doctor.location like %?1%", nativeQuery = true)
    public List<Doctor> findByLocation(String location);
    /////
    @Query(value = "delete from doctor where doctor.name like %?1%", nativeQuery = true)
    public List<Doctor> deleteByName(String name);
}