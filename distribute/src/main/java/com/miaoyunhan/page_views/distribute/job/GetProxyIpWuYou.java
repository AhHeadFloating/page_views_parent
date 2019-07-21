package com.miaoyunhan.page_views.distribute.job;


import com.alibaba.dubbo.config.annotation.Reference;
import com.miaoyunhan.api.entity.ProxyIp;
import com.miaoyunhan.page_views.distribute.utils.ProxyIpUtils;
import com.miaoyunhan.page_views.distribute.utils.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class GetProxyIpWuYou implements Runnable {

//    @Reference(check = false)
    private JmsTemplate jmsTemplate;

    public void getIpAndSendMq() throws Exception {

        ProxyIp ip = ProxyIpUtils.getIp();
        log.info(ip.toString());
        jmsTemplate.convertAndSend("views",ip);
    }

    @Override
    public void run() {
        try {
            if(jmsTemplate == null){
                jmsTemplate = SpringUtil.getBean(JmsTemplate.class);
            }
            getIpAndSendMq();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
