package com.miaoyunhan.page_views.persistence.service_impl;

import com.miaoyunhan.api.entity.Article;
import com.miaoyunhan.api.entity.BlogUser;
import com.miaoyunhan.api.service.BlogUserService;
import com.miaoyunhan.page_views.persistence.mapper.ArticleMapper;
import com.miaoyunhan.page_views.persistence.mapper.BlogUserMpper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@com.alibaba.dubbo.config.annotation.Service(timeout = 10000)
public class BlogUserServiceImpl implements BlogUserService {

    @Autowired
    private BlogUserMpper blogUserMpper;

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public int insertSelective(BlogUser blogUser){
        int i = blogUserMpper.insertSelective(blogUser);
        return i;
    }

    @Override
    public List<BlogUser> select(BlogUser blogUser){
        List<BlogUser> select = blogUserMpper.select(blogUser);
        return select;
    }

    @Override
    public int delByUserName(String csdnUserName) {
        //查询博客用户对象
        BlogUser blogUser = new BlogUser();
        blogUser.setBlogUserName(csdnUserName);
        List<BlogUser> blogUserList = blogUserMpper.select(blogUser);

        //如果博客对象不为空
        if(blogUserList != null && blogUserList.size() > 0){
            //删除博客对象
            blogUserMpper.delete(blogUser);

            //删除，并返回删除的文章条数
            Article article = new Article();
            article.setBlogUserId(blogUserList.get(0).getBlogUserId());
            int articleCount = articleMapper.selectCount(article);
            articleMapper.delete(article);
            return articleCount;
        }
        return 0;
    }
}

