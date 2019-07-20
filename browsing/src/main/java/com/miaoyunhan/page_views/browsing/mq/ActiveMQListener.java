package com.miaoyunhan.page_views.browsing.mq;

import com.alibaba.dubbo.config.annotation.Reference;
import com.miaoyunhan.api.entity.BlogUser;
import com.miaoyunhan.api.service.BlogUserService;
import com.miaoyunhan.page_views.browsing.crawl.CsdnAticleCrawl;
import com.miaoyunhan.page_views.browsing.crawl.GetCsdnPagesCrawl;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Component
public class ActiveMQListener {

	@Reference(check = false)
	private BlogUserService blogUserService;

	/**
	 * 保存博客UserName、url并向mq发送浏览任务
	 * @param csdnUserName csdn博客用户名
	 */
	@JmsListener(destination = "saveBlogAndStartViews")
	@Transactional
	public void saveBlogAndStartViews(String csdnUserName) throws Exception {
		//获取blog总页数
        GetCsdnPagesCrawl crawl = new GetCsdnPagesCrawl(csdnUserName);
		Integer pages = crawl.getPages();
        //保存BlogUserName
//		blogUserService.save(csdnUserName);
		//保存所有Blog的Url

		List<BlogUser> select = blogUserService.select(new BlogUser());
		System.out.println(select);
	}

    public static void main(String[] args) throws Exception {
        /*GetCsdnPagesCrawl crawl = new GetCsdnPagesCrawl("shujian_tianya");
        Integer pages = crawl.getPages();
        System.out.println(pages);*/
	}
}