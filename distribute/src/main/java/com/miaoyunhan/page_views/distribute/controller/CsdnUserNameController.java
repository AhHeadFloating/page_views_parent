package com.miaoyunhan.page_views.distribute.controller;

import com.miaoyunhan.api.entity.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "csdn")
public class CsdnUserNameController {

    @Autowired
    private JmsTemplate jmsTemplate;

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
}
