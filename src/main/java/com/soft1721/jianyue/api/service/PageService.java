package com.soft1721.jianyue.api.service;

import com.soft1721.jianyue.api.entity.Article;
import com.soft1721.jianyue.api.entity.User;

import java.util.List;

/**
 * Created by 张文旭 on 2019/4/15.
 */
public interface PageService {
    List<User> queryUsersBySql(int currPage, int pageSize);
}
