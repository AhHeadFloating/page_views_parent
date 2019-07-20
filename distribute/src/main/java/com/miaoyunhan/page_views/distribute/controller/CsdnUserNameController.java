package com.miaoyunhan.page_views.distribute.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.miaoyunhan.api.entity.BlogUser;
import com.miaoyunhan.api.entity.ResponseUtil;
import com.miaoyunhan.api.service.BlogUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CsdnUserNameController {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Reference
    private BlogUserService blogUserService;

    /**
     * 向browsing发送csdn用户名
     * @param csdnUserName csdn用户名
     * @return
     */
    @RequestMapping(value = "sendCsdnUserName")
    public Object sendCsdnUserName(String csdnUserName){
        jmsTemplate.convertAndSend("saveBlogAndStartViews",csdnUserName);
        return ResponseUtil.ok();
    }

    @RequestMapping(value = "findAll")
    public Object findAll(){
        List<BlogUser> select = blogUserService.select(new BlogUser());
        return select;
    }
}
