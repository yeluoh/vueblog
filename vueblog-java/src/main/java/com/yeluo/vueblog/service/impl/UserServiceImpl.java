package com.yeluo.vueblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yeluo.vueblog.entity.User;
import com.yeluo.vueblog.mapper.UserMapper;
import com.yeluo.vueblog.service.UserService;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
