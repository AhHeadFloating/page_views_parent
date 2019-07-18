package com.miaoyunhan.page_views.persistence.service_impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.miaoyunhan.api.entity.BlogUser;
import com.miaoyunhan.api.service.BlogUserService;
import com.miaoyunhan.page_views.persistence.mapper.BlogUserMpper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@com.alibaba.dubbo.config.annotation.Service(version = "1.0.0",timeout = 10000,interfaceClass = BlogUserService.class)
public class BlogUserServiceImpl implements BlogUserService {

    @Reference(version = "1.0.0",check = false)
    private BlogUserMpper blogUserMpper;

    @Override
    public int insertSelective(BlogUser blogUser){
        int i = blogUserMpper.insertSelective(blogUser);
        return i;
    }

    @Override
    public List<BlogUser> select(BlogUser blogUser){
        List<BlogUser> select = blogUserMpper.select(blogUser);
        return select;
    }
}
