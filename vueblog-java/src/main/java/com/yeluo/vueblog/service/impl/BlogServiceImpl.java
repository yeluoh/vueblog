package com.yeluo.vueblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yeluo.vueblog.entity.Blog;
import com.yeluo.vueblog.mapper.BlogMapper;
import com.yeluo.vueblog.service.BlogService;
import org.springframework.stereotype.Service;


@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

}
