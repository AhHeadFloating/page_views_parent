package com.miaoyunhan.page_views.browsing.crawl;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.rocks.BreadthCrawler;
import org.jsoup.select.Elements;

/**
 * 文章爬虫
 */
public class CsdnAticleCrawl extends BreadthCrawler {
    private int count = 0;
    private String blogUserName;
    public CsdnAticleCrawl(String crawlPath, boolean autoParse,String blogUserName) throws Exception {
        super(crawlPath, autoParse);
        this.blogUserName = blogUserName;
        GetCsdnPagesCrawl getCsdnPagesCrawl = new GetCsdnPagesCrawl(blogUserName);
        Integer pages = getCsdnPagesCrawl.getPages();
        for (int i = 01; i <= pages; i++) {
            this.addSeed("https://blog.csdn.net/" + blogUserName + "/article/list/" + i);
        }
        this.addRegex("https://blog.csdn.net/" + blogUserName + "/article/list/.*");
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
                    count++;
                    System.out.println(elements.get(i).attr("href"));
                    System.out.println("==========="+count+"=============");
                }
            }
        }
        this.setThreads(1);

    }

    public static void main(String[] args) {
        CsdnAticleCrawl csdnAticleCrawl = null;
        try {
            csdnAticleCrawl = new CsdnAticleCrawl("crawl",false,"yetaodiao");
            csdnAticleCrawl.start(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
