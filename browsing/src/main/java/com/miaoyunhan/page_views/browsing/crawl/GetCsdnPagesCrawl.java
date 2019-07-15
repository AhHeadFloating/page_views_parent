package com.miaoyunhan.page_views.browsing.crawl;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.rocks.BreadthCrawler;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ListIterator;

public class GetCsdnPagesCrawl extends BreadthCrawler {

    private String csdnUserName;

    private Integer pageNo = 1;

    public GetCsdnPagesCrawl(String csdnUserName) {
        super("Crawler", false);
        this.csdnUserName = csdnUserName;
        this.addSeed("https://blog.csdn.net/" + csdnUserName + "/article/list/" + pageNo++);
        this.addRegex("https://blog.csdn.net/" + csdnUserName + "/article/list/.*");
        this.setThreads(1);
    }

    @Override
    public void visit(Page page, CrawlDatums crawlDatums) {
        System.out.println(page.url());
        Elements select = page.select(".archive-list li a span");
        for (int i = 0; i < select.size(); i++) {
            System.out.println(select.get(i).text());
        }
    }

    public static void main(String[] args) throws Exception {
        GetCsdnPagesCrawl crawl = new GetCsdnPagesCrawl("lsj960922");
        crawl.start(1);
    }
}
