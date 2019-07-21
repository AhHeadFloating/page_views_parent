package com.miaoyunhan.page_views.distribute.mq;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class MqUtil {

	private final JmsTemplate jmsTemplate;

	@Autowired
	public MqUtil(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public void send(String queueName,Object message){
		jmsTemplate.convertAndSend(new ActiveMQQueue(queueName),message);
	}

}