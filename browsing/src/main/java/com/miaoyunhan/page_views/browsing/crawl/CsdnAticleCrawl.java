package com.miaoyunhan.page_views.browsing.crawl;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.rocks.BreadthCrawler;
import com.miaoyunhan.api.entity.Article;
import com.miaoyunhan.api.entity.BlogUser;
import com.miaoyunhan.api.service.ArticleService;
import com.miaoyunhan.api.service.BlogUserService;
import com.miaoyunhan.page_views.browsing.utils.SpringUtil;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Date;

/**
 * 文章爬虫
 */
public class CsdnAticleCrawl extends BreadthCrawler {

    private BlogUserService blogUserService;

    private ArticleService articleService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    {
        this.blogUserService = SpringUtil.getBean(BlogUserService.class);
        this.articleService = SpringUtil.getBean(ArticleService.class);
        this.redisTemplate = SpringUtil.getBean(StringRedisTemplate.class);
    }
    private int count = 0;
    private String blogUserName;
    private BlogUser blogUser;
    public CsdnAticleCrawl(String crawlPath, boolean autoParse) throws Exception {
        super(crawlPath, autoParse);
        this.blogUserName = crawlPath;
        GetCsdnPagesCrawl getCsdnPagesCrawl = new GetCsdnPagesCrawl(this.blogUserName);
        Integer pages = getCsdnPagesCrawl.getPages();
        for (int i = 1; i <= pages; i++) {
            this.addSeed("https://blog.csdn.net/" + this.blogUserName + "/article/list/" + i);
        }
        this.addRegex("https://blog.csdn.net/" + this.blogUserName + "/article/list/.*");
        blogUser = new BlogUser();
        blogUser.setBlogUserName(blogUserName);
        blogUser.setCreateTime(new Date());
        blogUser.setBlogType(1);


        Long blogUserId = redisTemplate.opsForValue().increment("blogUserId");
        blogUser.setBlogUserId(blogUserId);
        blogUserService.insertSelective(this.blogUser);
        this.setThreads(3);
    }

    @Override
    public void visit(Page page, CrawlDatums crawlDatums) {
        if(page.matchUrl("https://blog.csdn.net/" + this.blogUserName + "/article/list/.*")){
            Elements elements = page.select(".article-item-box h4 a");
            for (int i = 0; i < elements.size(); i++) {
                String matchUrl = "https://blog.csdn.net/"+this.blogUserName+"/article/details/.*";
                String href = elements.get(i).attr("href");
                if(href.matches(matchUrl)){
                    count++;

                    System.out.println(elements.get(i).text());
                    System.out.println(elements.get(i).attr("href"));
                    System.out.println("==========="+count+"=============");
                    Article article = new Article(null,blogUser.getBlogUserId() ,elements.get(i).text() , href);
                    try{
                        int row = articleService.insertSlective(article);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    System.out.println(article);
                }
            }
        }
        this.setThreads(1);
    }

}
