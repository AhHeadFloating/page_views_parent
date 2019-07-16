package com.miaoyunhan.page_views.browsing.crawl;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.rocks.BreadthCrawler;

/**
 * 文章爬虫
 */
public class CsdnAticleCrawl extends BreadthCrawler {
    public CsdnAticleCrawl(String crawlPath, boolean autoParse) {
        super(crawlPath, autoParse);
    }

    @Override
    public void visit(Page page, CrawlDatums crawlDatums) {

    }
}
