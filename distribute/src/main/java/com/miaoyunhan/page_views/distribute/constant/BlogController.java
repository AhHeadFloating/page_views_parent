package com.miaoyunhan.page_views.distribute.constant;

import com.miaoyunhan.page_views.distribute.mq.MqUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogController {

    @Autowired
    private JmsTemplate jmsTemplate;

    @RequestMapping(value = "addBlog")
    public String addBlog(String blogUserName){
        jmsTemplate.convertAndSend("queueTest","你好呀");
        System.out.println();
        return String.valueOf(System.currentTimeMillis());
    }
}
