package com.yeluo.vueblog.controller;


import com.yeluo.vueblog.common.lang.Result;
import com.yeluo.vueblog.entity.User;
import com.yeluo.vueblog.service.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequiresAuthentication
    @GetMapping("/index")
    public Object index(){
        User user = userService.getById(1L);
        return Result.succ(user);

    }
    @PostMapping("/save")
    public Object save(@Validated @RequestBody User user){

        return Result.succ(user);

    }

}
