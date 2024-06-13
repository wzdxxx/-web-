package com.pro.controller;


import com.pro.dao.DoctorDao;
import com.pro.entity.Doctor;
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
@RequestMapping ("/doctor")
public class DoctorController {
    @Resource
    private DoctorDao doctorDao;
    @RequestMapping("/list3")
    public ModelAndView list3() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("doctorlist", doctorDao.findAll());
        mav.setViewName("reserveAdd");
        return mav;
    }
    @RequestMapping("/list")
    public ModelAndView list() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("doctorlist", doctorDao.findAll());
        mav.setViewName("doctorList");
        return mav;
    }
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(Doctor doctor) {
        doctorDao.save(doctor);
        return "forward:/doctor/list";
    }
    @RequestMapping("/preUpdate/{id}")
    public ModelAndView preUpdate(@PathVariable("id") Integer id) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("doctor", doctorDao.getOne(id));
        mav.setViewName("doctorUpdate");
        return mav;
    }
    @PostMapping(value = "/update")
    public String update(Doctor doctor) {
        doctorDao.save(doctor);
        return "forward:/doctor/list";
    }
    @GetMapping("/delete")
    public String delete(Integer id) {
        doctorDao.deleteById(id);
        return "forward:/doctor/list";
    }
    @RequestMapping("/list2")
    public ModelAndView list2(Doctor doctor) {
        ModelAndView mav = new ModelAndView();
        List<Doctor> doctorList = doctorDao.findAll(new Specification<Doctor>() {
            @Override
            public Predicate toPredicate(Root<Doctor> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if (doctor != null) {
                    if (doctor.getName() != null && !"".equals(doctor.getName())) {
                        predicate.getExpressions().add(cb.like(root.get("name"), "%" + doctor.getName() + "%"));
                    }
                    if (doctor.getTime() != null && !"".equals(doctor.getTime())) {
                        predicate.getExpressions().add(cb.like(root.get("time"), "" + doctor.getTime() + "%"));
                    }
                    if (doctor.getDepartment() != null && !"".equals(doctor.getDepartment())) {
                        predicate.getExpressions().add(cb.like(root.get("department"), "%" + doctor.getDepartment() + "%"));
                    }
                    if (doctor.getLocation() != null && !"".equals(doctor.getLocation())) {
                        predicate.getExpressions().add(cb.like(root.get("location"), "%" + doctor.getLocation() + "%"));
                    }
                }
                return predicate;
            }
        });
        mav.addObject("doctor", doctor);
        mav.addObject("doctorlist", doctorList);
        mav.setViewName("doctorlist");
        return mav;
    }

    //查询
    @ResponseBody
    @RequestMapping("/queryname")
    public List<Doctor> findByName(String name) {
        return doctorDao.findByName(name);
    }
    @ResponseBody
    @RequestMapping("/querytime")
    public List<Doctor> findByTime(String time) {
        return doctorDao.findByTime(time);
    }
    @ResponseBody
    @RequestMapping("/querydepartment")
    public List<Doctor> findByDepartment(String department) {
        return doctorDao.findByDepartment(department);
    }
    @ResponseBody
    @RequestMapping("/querylocation")
    public List<Doctor> findByLocation(String location) {
        return doctorDao.findByLocation(location);
    }
    ////////////////////////////
    @ResponseBody
    @PostMapping(value = "/a_add")
    public List<Doctor> a_add(@RequestParam("name")String name,
                                  @RequestParam("time")String time,
                                  @RequestParam("department")String department,
                                  @RequestParam("location")String location) {
        Doctor doctor = new Doctor();
        doctor.setName(name);
        doctor.setTime(time);
        doctor.setDepartment(department);
        doctor.setLocation(location);
        doctorDao.save(doctor);
        return doctorDao.findAll();
    }
    @ResponseBody
    @GetMapping(value = "/a_findbyid")
    public Optional<Doctor> findById(@RequestParam("id")int id) {
        return doctorDao.findById(id);
    }
    @ResponseBody
    @GetMapping(value = "/a_deleteid")
    public String a_deleteid(@RequestParam("id")Integer id){
        if (findById(id).isPresent()){
            doctorDao.deleteById(id);
            return "删除成功！";
        }
        else return "删除失败！";
    }
    @ResponseBody
    @PutMapping(value = "/a_update")
    public List<Doctor> a_update(@RequestParam("id")Integer id,
                                 @RequestParam("name")String name,
                                 @RequestParam("time")String time,
                                 @RequestParam("department")String department,
                                 @RequestParam("location")String location) {

        Doctor doctor = new Doctor();
        doctor.setId(id);
        doctor.setName(name);
        doctor.setTime(time);
        doctor.setDepartment(department);
        doctor.setLocation(location);
        doctorDao.save(doctor);
        return doctorDao.findAll();
    }
    @ResponseBody
    @GetMapping(value = "/a_querybyid")
    public Doctor queryById(@RequestParam("id")Integer id) {
        return doctorDao.findById(id).get();
    }
    @ResponseBody
    @GetMapping(value = "/a_query")
    public List<Doctor> findById() {
        return doctorDao.findAll();
    }
}
