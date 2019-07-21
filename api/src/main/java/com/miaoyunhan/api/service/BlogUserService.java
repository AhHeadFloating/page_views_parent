package com.miaoyunhan.api.service;

import com.miaoyunhan.api.entity.BlogUser;

import java.util.List;

public interface BlogUserService {

    int insertSelective(BlogUser blogUser);

    List<BlogUser> select(BlogUser blogUser);

    int delByUserName(String csdnUserName);
}
