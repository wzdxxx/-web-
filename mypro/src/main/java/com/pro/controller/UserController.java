package com.pro.controller;


import com.pro.dao.UserDao;
import com.pro.entity.Doctor;
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
@RequestMapping ("/user")
public class UserController {
    @Resource
    private UserDao userDao;

    @RequestMapping(value = "/log", method = RequestMethod.POST)
    public String log(User user) {
        userDao.save(user);
        return "forward:/reserve/list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(User user) {
        userDao.save(user);
        return "forward:/user/list";
    }
    @RequestMapping("/preUpdate/{id}")
    public ModelAndView preUpdate(@PathVariable("id") Integer id) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("user", userDao.getOne(id));
        mav.setViewName("userUpdate");
        return mav;
    }
    @PostMapping(value = "/update")
    public String update(User user) {
        userDao.save(user);
        return "forward:/user/list";
    }
    @GetMapping("/delete")
    public String delete(Integer id) {
        userDao.deleteById(id);
        return "forward:/user/list";
    }

    @RequestMapping("/list2")
    public ModelAndView list2(User user) {
        ModelAndView mav = new ModelAndView();
        List<User> userList = userDao.findAll(new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if (user != null) {
                    if (user.getTel() != null && !"".equals(user.getTel())) {
                        predicate.getExpressions().add(cb.like(root.get("name"), "%" + user.getTel() + "%"));
                    }
                    if (user.getSex() != null && !"".equals(user.getSex())) {
                        predicate.getExpressions().add(cb.like(root.get("time"), "" + user.getSex() + "%"));
                    }
                }
                return predicate;
            }
        });
        mav.addObject("user", user);
        mav.addObject("userlist", userList);
        mav.setViewName("userlist");
        return mav;
    }
    //////////////////////
    @ResponseBody
    @PostMapping(value = "/a_add")
    public List<User> a_add(@RequestParam("tel")String tel,
                              @RequestParam("sex")String sex) {
        User user = new User();
        user.setTel(tel);
        user.setSex(sex);
        userDao.save(user);
        return userDao.findAll();
    }
    @ResponseBody
    @GetMapping(value = "/a_findbyid")
    public Optional<User> findById(@RequestParam("id")int id) {
        return userDao.findById(id);
    }
    @GetMapping(value = "/a_deleteid")
    public String a_deleteid(@RequestParam("id")Integer id){
        if (findById(id).isPresent()){
            userDao.deleteById(id);
            return "删除成功！";
        }
        else return "删除失败！";
    }
    @ResponseBody
    @PutMapping(value = "/a_update")
    public List<User> a_update(@RequestParam("id")Integer id,
                                 @RequestParam("tel")String tel,
                                 @RequestParam("sex")String sex) {
        User user = new User();
        user.setId(id);
        user.setTel(tel);
        user.setSex(sex);
        userDao.save(user);
        return userDao.findAll();
    }
    @ResponseBody
    @GetMapping(value = "/a_querybyid")
    public User queryById(@RequestParam("id")Integer id) {
        return userDao.findById(id).get();
    }
    @ResponseBody
    @GetMapping(value = "/a_query")
    public List<User> findById() {
        return userDao.findAll();
    }
}
