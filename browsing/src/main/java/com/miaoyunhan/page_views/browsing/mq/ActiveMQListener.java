package com.miaoyunhan.page_views.browsing.mq;

import com.alibaba.dubbo.config.annotation.Reference;
import com.miaoyunhan.api.entity.Article;
import com.miaoyunhan.api.entity.BlogUser;
import com.miaoyunhan.api.entity.ProxyIp;
import com.miaoyunhan.api.service.ArticleService;
import com.miaoyunhan.api.service.BlogUserService;
import com.miaoyunhan.page_views.browsing.crawl.CsdnAticleCrawl;
import com.miaoyunhan.page_views.browsing.crawl.GetCsdnPagesCrawl;
import com.miaoyunhan.page_views.browsing.utils.BrowseThreadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Slf4j
public class ActiveMQListener {

	@Reference(check = false)
	private BlogUserService blogUserService;

	@Reference(check = false,timeout = 2000)
	private ArticleService articleService;

	/**
	 * 保存博客UserName、url并向mq发送浏览任务
	 * @param csdnUserName csdn博客用户名
	 */
	@JmsListener(destination = "saveBlogAndStartViews")
	@Transactional
	public void saveBlogAndStartViews(String csdnUserName) throws Exception {

	    //删除数据库中关于这个博客的数据
	    int rows = blogUserService.delByUserName(csdnUserName);

        //开始往数据库中添加
        Long startTime = System.currentTimeMillis();
        CsdnAticleCrawl shujian_tianya = new CsdnAticleCrawl(csdnUserName);
        shujian_tianya.start(1);
        log.info("===============爬取博客所用时间（秒）=====================");
        System.out.println((System.currentTimeMillis() - startTime)/1000);
        log.info("==========================================================");
    }

	/**
	 * 浏览一次数据库里的博客
	 * @param proxyIp
	 */
	@JmsListener(destination = "views")
	@Transactional
	public void views(ProxyIp proxyIp) throws Exception {
		log.info(proxyIp.toString());
		List<Article> articleList = articleService.select(new Article());
		log.info("获取文章列表" + articleList.toString());

		BrowseThreadUtil browseThreadUtil = new BrowseThreadUtil();
		browseThreadUtil.setArticleList(articleList);
		Thread thread = new Thread(browseThreadUtil);
		thread.setName("wuYouJob");
		thread.start();

	}

    public static void main(String[] args) throws Exception {
        /*GetCsdnPagesCrawl crawl = new GetCsdnPagesCrawl("shujian_tianya");
        Integer pages = crawl.getPages();
        System.out.println(pages);*/
	}
}