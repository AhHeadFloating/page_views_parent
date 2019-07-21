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
public class CsdnUserController {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Reference(check = false)
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

    /**
     * 删除数据库中这个博客用户和下面的所有文章
     * @param blogUserName
     * @return
     */
    @RequestMapping(value = "delBlogUser")
    public Object delBlogUser(String blogUserName){
        int i = blogUserService.delByUserName(blogUserName);
        return ResponseUtil.ok("删除了" + i + "条数据");
    }

    /**
     * 返回当数据库中的所有博客用户
     * @return
     */
    @RequestMapping(value = "findAll")
    public Object findAll(){
        List<BlogUser> select = blogUserService.select(new BlogUser());
        return select;
    }
}
