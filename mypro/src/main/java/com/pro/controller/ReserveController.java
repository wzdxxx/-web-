package com.pro.controller;

import com.pro.dao.DoctorDao;
import com.pro.dao.ReserveDao;
import com.pro.dao.UserDao;
import com.pro.entity.Doctor;
import com.pro.entity.Reserve;
import com.pro.entity.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype .Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;
import javax.persistence.criteria.Predicate;
//图书控制器
@Controller
@RequestMapping ("/reserve")
public class ReserveController {
    @Resource
    private ReserveDao reserveDao;
    @Resource
    private DoctorDao doctorDao;
    @Resource
    private UserDao userDao;

    @RequestMapping("/list")
    public ModelAndView list() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("reservelist", reserveDao.findAll());
        mav.setViewName("reserveList");
        return mav;
    }
    @RequestMapping("/add/{id}")
    public ModelAndView preUpdate(@PathVariable("id") Integer id) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("doctor", doctorDao.getOne(id));
        mav.setViewName("seccess");
        return mav;
    }
    @RequestMapping(value = "/ret", method = RequestMethod.POST)
    public String add(Doctor doctor) {
        Reserve reserve = new Reserve();
        reserve.setName(doctor.getName());
        reserve.setTime(doctor.getTime());
        reserve.setDepartment(doctor.getDepartment());
        reserve.setLocation(doctor.getLocation());
        reserve.setTel("15839067862");
        reserve.setSex("男");
        reserveDao.save(reserve);
        return "forward:/reserve/list";
    }
    ////////////////////////////
    @ResponseBody
    @PostMapping(value = "/a_add")
    public List<Reserve> a_add(@RequestParam("id")Integer id,
                               @RequestParam("tel")String tel,
                               @RequestParam("sex")String sex,
                               @RequestParam("name")String name,
                              @RequestParam("time")String time,
                              @RequestParam("department")String department,
                              @RequestParam("location")String location) {
        Reserve reserve = new Reserve();
        reserve.setId(id);
        reserve.setTel(tel);
        reserve.setSex(sex);
        reserve.setName(name);
        reserve.setTime(time);
        reserve.setDepartment(department);
        reserve.setLocation(location);
        reserveDao.save(reserve);
        return reserveDao.findAll();
    }
    @ResponseBody
    @GetMapping(value = "/a_findbyid")
    public Optional<Reserve> findById(@RequestParam("id")int id) {
        return reserveDao.findById(id);
    }
    @ResponseBody
    @GetMapping(value = "/a_deleteid")
    public String a_deleteid(@RequestParam("id")Integer id){
        if (findById(id).isPresent()){
            reserveDao.deleteById(id);
            return "删除成功！";
        }
        else return "删除失败！";
    }
    @ResponseBody
    @PutMapping(value = "/a_update")
    public List<Reserve> a_update(@RequestParam("id")Integer id,
                                  @RequestParam("tel")String tel,
                                  @RequestParam("sex")String sex,
                                 @RequestParam("name")String name,
                                 @RequestParam("time")String time,
                                 @RequestParam("department")String department,
                                 @RequestParam("location")String location) {
        Reserve reserve = new Reserve();
        reserve.setId(id);
        reserve.setTel(tel);
        reserve.setSex(sex);
        reserve.setName(name);
        reserve.setTime(time);
        reserve.setDepartment(department);
        reserve.setLocation(location);
        reserveDao.save(reserve);
        return reserveDao.findAll();
    }
    @ResponseBody
    @GetMapping(value = "/a_querybyid")
    public Reserve queryById(@RequestParam("id")Integer id) {
        return reserveDao.findById(id).get();
    }
    @ResponseBody
    @GetMapping(value = "/a_query")
    public List<Reserve> findById() {
        return reserveDao.findAll();
    }
}

