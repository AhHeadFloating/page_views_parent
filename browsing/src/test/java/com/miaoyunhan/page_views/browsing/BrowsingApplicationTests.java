package com.miaoyunhan.page_views.browsing;

import com.alibaba.dubbo.config.annotation.Reference;
import com.miaoyunhan.api.entity.BlogUser;
import com.miaoyunhan.api.service.BlogUserService;
import com.miaoyunhan.page_views.browsing.crawl.CsdnAticleCrawl;
import com.miaoyunhan.page_views.browsing.utils.SpringUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BrowsingApplicationTests {


    @Reference(check = false)
    private BlogUserService blogUserService;


    @Test
    public void Test1() throws Exception {
        //删除数据库中关于这个博客的数据
        int rows = blogUserService.delByUserName("bin2277904333");
        Long startTime = System.currentTimeMillis();
        CsdnAticleCrawl shujian_tianya = new CsdnAticleCrawl("bin2277904333");
        shujian_tianya.start(1);
        System.out.println("==========================================");
        System.out.println((System.currentTimeMillis() - startTime)/1000);
        System.out.println("==========================================");

    }

}
