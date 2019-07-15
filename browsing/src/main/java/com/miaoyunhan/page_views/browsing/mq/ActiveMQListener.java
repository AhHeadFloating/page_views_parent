package com.miaoyunhan.page_views.browsing.mq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ActiveMQListener {

	@JmsListener(destination = "queueTest")
	public void processMessage(String content) {
		System.out.println(content);
	}

	/**
	 * 保存博客UserName、url并向mq发送浏览任务
	 * @param csdnUserName csdn博客用户名
	 */
	@JmsListener(destination = "saveBlogAndStartViews")
	@Transactional
	public void saveBlogAndStartViews(String csdnUserName){
		//获取blog总页数
		//保存BlogUserName
		//保存所有Blog的Url

	}
}