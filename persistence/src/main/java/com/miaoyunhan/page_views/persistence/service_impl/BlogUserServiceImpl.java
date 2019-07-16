package com.miaoyunhan.page_views.persistence.service_impl;

import com.miaoyunhan.api.service.BlogUserService;
import com.miaoyunhan.api.service.RemoteUserService;
import org.springframework.stereotype.Service;

@Service
@com.alibaba.dubbo.config.annotation.Service(version = "1.0.0",timeout = 10000,interfaceClass = RemoteUserService.class)
public class BlogUserServiceImpl implements BlogUserService {

}
