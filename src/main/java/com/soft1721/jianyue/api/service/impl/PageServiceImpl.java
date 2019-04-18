package com.soft1721.jianyue.api.service.impl;

import com.soft1721.jianyue.api.entity.Article;
import com.soft1721.jianyue.api.entity.User;
import com.soft1721.jianyue.api.mapper.ArticleMapper;
import com.soft1721.jianyue.api.mapper.UserMapper;
import com.soft1721.jianyue.api.service.PageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 张文旭 on 2019/4/15.
 */
@Service
public class PageServiceImpl implements PageService {
    @Resource
    private UserMapper userMapper;


    @Override
    public List<User> queryUsersBySql(int currPage, int pageSize) {
        List<User> users = userMapper.queryUsersByArray(currPage,pageSize);
        int  firstIndex = (currPage - 1) * pageSize;
        int lastIndex = currPage*pageSize;
        return users.subList(firstIndex,lastIndex);
    }
}
