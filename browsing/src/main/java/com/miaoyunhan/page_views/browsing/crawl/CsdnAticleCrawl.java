package com.miaoyunhan.page_views.browsing.crawl;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.rocks.BreadthCrawler;
import com.miaoyunhan.api.entity.Article;
import com.miaoyunhan.api.entity.BlogUser;
import com.miaoyunhan.api.service.ArticleService;
import com.miaoyunhan.api.service.BlogUserService;
import com.miaoyunhan.page_views.browsing.utils.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.select.Elements;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Date;

/**
 * 文章爬虫
 */
@Slf4j
public class CsdnAticleCrawl extends BreadthCrawler {

    //在代码块里注入
    private BlogUserService blogUserService;
    private ArticleService articleService;
    private StringRedisTemplate redisTemplate;

    //注入服务
    {
        this.blogUserService = SpringUtil.getBean(BlogUserService.class);
        this.articleService = SpringUtil.getBean(ArticleService.class);
        this.redisTemplate = SpringUtil.getBean(StringRedisTemplate.class);
    }

    //文章数量
    private int articleCount = 0;
    //博客用户名
    private String blogUserName;
    //博客用户对象
    private BlogUser blogUser;

    /**
     *  构造方法
     * @param blogUserName 博客用户名，爬虫运行的文件也会保存到 Crawler/blogUserName 下
     * @throws Exception
     */
    public CsdnAticleCrawl(String blogUserName) throws Exception {
        super("Crawler/"+blogUserName, false);

        //因为visit()方法要用到blogUserName参数，放到成员变量
        this.blogUserName = blogUserName;

        //从web页面获取博客页数
        GetCsdnPagesCrawl getCsdnPagesCrawl = new GetCsdnPagesCrawl(this.blogUserName);
        Integer pages = getCsdnPagesCrawl.getPages();

        //把1 - pages页的url放到种子页面
        for (int i = 1; i <= pages; i++) {
            this.addSeed("https://blog.csdn.net/" + this.blogUserName + "/article/list/" + i);
        }

        //爬取的页面要匹配这个正则
        this.addRegex("https://blog.csdn.net/" + this.blogUserName + "/article/list/.*");

        //保存BlogUser对象
        blogUser = new BlogUser();
        blogUser.setBlogUserName(this.blogUserName);
        blogUser.setCreateTime(new Date());
        blogUser.setBlogType(1);
        Long blogUserId = redisTemplate.opsForValue().increment("blogUserId");
        blogUser.setBlogUserId(blogUserId);
        blogUserService.insertSelective(this.blogUser);

        //设置线程数
        this.setThreads(3);
    }

    @Override
    public void visit(Page page, CrawlDatums crawlDatums) {
        //如果匹配，保存当前页面数据
        if(page.matchUrl("https://blog.csdn.net/" + this.blogUserName + "/article/list/.*")){
            //获取标题上的a标签
            Elements elements = page.select(".article-item-box h4 a");

            //循环遍历页面上博客列表中的数据
            for (int i = 0; i < elements.size(); i++) {
                String matchUrl = "https://blog.csdn.net/"+this.blogUserName+"/article/details/.*";
                String href = elements.get(i).attr("href");
                if(href.matches(matchUrl)){
                    //博客数量（暂时未用到）
                    articleCount++;

                    //打印爬到的信息
                    log.info(elements.get(i).text());
                    log.info(elements.get(i).attr("href"));
                    log.info("==========="+articleCount+"=============");

                    Article article = null;
                    try{
                        //把页面中的当前条保存到数据库
                        article = new Article(null,blogUser.getBlogUserId() ,elements.get(i).text() , href);
                        int row = articleService.insertSlective(article);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
        this.setThreads(1);
    }

}
