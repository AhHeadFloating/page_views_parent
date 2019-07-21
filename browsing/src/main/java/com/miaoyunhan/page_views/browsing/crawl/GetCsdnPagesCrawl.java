package com.miaoyunhan.page_views.browsing.crawl;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.rocks.BreadthCrawler;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.select.Elements;

/**
 * 获取博客总数爬虫
 */
@Slf4j
public class GetCsdnPagesCrawl extends BreadthCrawler {

    private Integer total = 0;

    /**
     * 构造方法
     * 只添加第一页，只爬取第一页就够了
     * @param csdnUserName
     */
    public GetCsdnPagesCrawl(String csdnUserName) {
        super("Crawler", false);
        this.addSeed("https://blog.csdn.net/" + csdnUserName + "/article/list/" + 1);
        this.addRegex("https://blog.csdn.net/" + csdnUserName + "/article/list/.*");
        this.setThreads(1);
    }

    /**
     * 爬取列表页左边的归档模块，因为页数不是直接加载的，所以访问不到
     * @param page
     * @param crawlDatums
     */
    @Override
    public void visit(Page page, CrawlDatums crawlDatums) {
        //把归档页表篇数加起来
        log.info(page.url());
        Elements select = page.select(".archive-list li a span");
        for (int i = 0; i < select.size(); i++) {
            //获取到的有个篇字，去掉，加到total
            total += new Integer(select.get(i).text().replaceAll("篇",""));
        }
    }

    /**
     * 获取文章总页数
     * @return
     * @throws Exception
     */
    public Integer getPages() throws Exception {
        //如果total等于0，认为这个对象还没执行过start方法，先执行一次
        if(total == 0){
            this.start(1);
        }

        //如果执行完start方法total还是为0，说明这个用户一篇博客都没有
        if(total == 0){
            return 1;
        }

        //计算页数并返回，csdn博客每页20条
        Integer pages = total/20;
        if((total%20) >= 1){
            pages++;
        }
        return pages;
    }

    /**
     * 获取文章总数
     * @return
     * @throws Exception
     */
    public Integer getTotal() throws Exception {
        if(total != 0){
            return total;
        }
        this.start(1);
        return total;
    }
}
