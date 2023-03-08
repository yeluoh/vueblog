package com.yeluo.vueblog.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yeluo.vueblog.common.lang.Result;
import com.yeluo.vueblog.entity.Blog;
import com.yeluo.vueblog.service.BlogService;
import com.yeluo.vueblog.util.ShiroUtill;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


@RestController
public class BlogController {

    @Autowired
    BlogService blogService;

    @GetMapping("blogs")
    public Result list(@RequestParam(defaultValue = "1") Integer currentPage){

        Page page = new Page(currentPage,5);
        IPage pageData = blogService.page(page, new QueryWrapper<Blog>().orderByDesc("created"));

        return Result.succ(pageData);
    }

    @GetMapping("/blog/{id}")
    public Result detail(@PathVariable(name = "id") Long id){
        Blog blog = blogService.getById(id);
        Assert.notNull(blog,"该博客已消失");

        return Result.succ(blog);
    }

    @RequiresAuthentication
    @PostMapping("/blog/edit")
    public Result edit(@Validated @RequestBody Blog blog){

        Blog temp = null;
        if (blog.getId() != null){
            temp= blogService.getById(blog.getId());
            // 只能编辑自己的内容
            Assert.isTrue(temp.getUserId() == ShiroUtill.getProfile().getId(),"权限不足");
        }else {

            temp=new Blog();
            temp.setUserId(ShiroUtill.getProfile().getId());
            temp.setCreated(LocalDateTime.now());
            temp.setStatus(0);

        }

        BeanUtil.copyProperties(blog,temp,"id","userId","created","status");
        blogService.saveOrUpdate(temp);

        return Result.succ(null);
    }

}
