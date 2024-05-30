package com.bookcode.controller;

import com. bookcode.dao.UserCrudRepository;
import com. bookcode. entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype .Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@Controller

@RequestMapping (path="/demo")
public class UserController {
    @Autowired
    private UserCrudRepository userCrudRepository;

    @GetMapping(path = "/add")
    @ResponseBody
    public String addNewUser(@RequestParam String firstname, @RequestParam
            String lastname) {
        User user = new User(firstname, lastname);
        userCrudRepository.save(user);
        return "Saved";
    }

    @GetMapping(path = "/finduser/{lastname}")
    @ResponseBody
    public String finduser(@PathVariable("lastname") String lastname) {
        List<User> userList = userCrudRepository.findByLastName(lastname);
        String users = " ";
        for (User user : userList) {users += user.toString() + " ";};
        return users;
    }
}