package com.miaoyunhan.page_views.browsing.mq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQListener {

	@JmsListener(destination = "queueTest")
	public void processMessage(String content) {
		System.out.println(content);
	}

}