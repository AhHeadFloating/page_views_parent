package com.miaoyunhan.page_views.browsing.crawl;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.rocks.BreadthCrawler;
import org.jsoup.select.Elements;

/**
 * 获取博客总数爬虫
 */
public class GetCsdnPagesCrawl extends BreadthCrawler {

    private Integer total = 1;

    public GetCsdnPagesCrawl(String csdnUserName) {
        super("Crawler", false);
        this.addSeed("https://blog.csdn.net/" + csdnUserName + "/article/list/" + 1);
        this.addRegex("https://blog.csdn.net/" + csdnUserName + "/article/list/.*");
        this.setThreads(1);
    }

    @Override
    public void visit(Page page, CrawlDatums crawlDatums) {
        System.out.println(page.url());
        Elements select = page.select(".archive-list li a span");
        for (int i = 0; i < select.size(); i++) {
            total += new Integer(select.get(i).text().replaceAll("篇",""));
        }
    }

    public Integer getPages() throws Exception {
        this.start(1);
        Integer pages = total/20;
        if((total%20) >= 1){
            pages++;
        }
        return pages;
    }
}
