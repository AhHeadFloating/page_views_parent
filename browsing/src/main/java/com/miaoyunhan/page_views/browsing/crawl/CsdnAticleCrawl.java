package com.miaoyunhan.page_views.browsing.crawl;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.rocks.BreadthCrawler;
import com.miaoyunhan.api.entity.Article;
import com.miaoyunhan.api.entity.BlogUser;
import com.miaoyunhan.api.service.ArticleService;
import com.miaoyunhan.api.service.BlogUserService;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * 文章爬虫
 */
public class CsdnAticleCrawl extends BreadthCrawler {
    /*@Autowired
    private BlogUserService blogUserService;

    @Autowired
    private ArticleService articleService;*/

    private int count = 0;
    private String blogUserName;
    private BlogUser blogUser;
    public CsdnAticleCrawl(String crawlPath, boolean autoParse,String blogUserName) throws Exception {
        super(crawlPath, autoParse);
        this.blogUserName = blogUserName;
        GetCsdnPagesCrawl getCsdnPagesCrawl = new GetCsdnPagesCrawl(blogUserName);
        Integer pages = getCsdnPagesCrawl.getPages();
        for (int i = 01; i <= pages; i++) {
            this.addSeed("https://blog.csdn.net/" + blogUserName + "/article/list/" + i);
        }
        this.addRegex("https://blog.csdn.net/" + blogUserName + "/article/list/.*");
        blogUser = new BlogUser();
        blogUser.setBlogUserName(blogUserName);
        blogUser.setCreateTime(new Date());
        blogUser.setBlogType(1);
//        blogUserService.insertSelective(blogUser);
    }

    @Override
    public void visit(Page page, CrawlDatums crawlDatums) {
        if(page.matchUrl("https://blog.csdn.net/" + blogUserName + "/article/list/.*")){
            Elements elements = page.select(".article-item-box h4 a");
            for (int i = 0; i < elements.size(); i++) {
                System.out.println(elements.get(i).text());
                String matchUrl = "https://blog.csdn.net/"+blogUserName+"/article/details/.*";
                String href = elements.get(i).attr("href");
                if(href.matches(matchUrl)){
//                    page.select();
                    count++;
                    System.out.println(elements.get(i).attr("href"));
                    System.out.println("==========="+count+"=============");
//                    new Article(null,,blogUser.getBlogUserId(),href);
//                    articleService.insertSlective();
                }
            }
        }
        this.setThreads(1);

    }

}
