package com.soft1721.jianyue.api.service.impl;

import com.soft1721.jianyue.api.entity.Like;
import com.soft1721.jianyue.api.service.LikeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by 张文旭 on 2019/4/12.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LikeServiceImplTest {
    @Resource
    private LikeService likeService;
    @Test
    public void getLike() throws Exception {
        likeService.getLike(29,1);
    }

    @Test
    public void getLikesByUId() throws Exception {
        likeService.getLikesByUId(29);
    }

    @Test
    public void insertLike() throws Exception {
        Like like = new Like();
        like.setFromUId(31);
        like.setToAId(2);
        likeService.insertLike(like);
    }

    @Test
    public void deleteLike() throws Exception {
        likeService.deleteLike(31,2);
    }

}