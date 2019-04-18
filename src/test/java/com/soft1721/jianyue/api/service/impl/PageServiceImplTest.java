package com.soft1721.jianyue.api.service.impl;

import com.soft1721.jianyue.api.service.PageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by 张文旭 on 2019/4/15.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PageServiceImplTest {
    @Resource
    private PageService pageService;
    @Test
    public void queryArticleBySql() throws Exception {
         System.out.println(pageService.queryUsersBySql(2,3));
    }

}