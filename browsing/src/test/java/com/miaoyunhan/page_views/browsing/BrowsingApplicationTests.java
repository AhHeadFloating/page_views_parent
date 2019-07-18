package com.miaoyunhan.page_views.browsing;

import com.miaoyunhan.page_views.browsing.crawl.CsdnAticleCrawl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BrowsingApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void Test1(){
        CsdnAticleCrawl csdnAticleCrawl = null;
        try {
            csdnAticleCrawl = new CsdnAticleCrawl("crawl",false,"yetaodiao");
            csdnAticleCrawl.start(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
