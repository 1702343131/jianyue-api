package com.soft1721.jianyue.api.service.impl;

import com.soft1721.jianyue.api.entity.Follow;
import com.soft1721.jianyue.api.service.FollowService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by 张文旭 on 2019/4/10.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FollowServiceImplTest {
    @Resource
    private FollowService followService;
    @Test
    public void getFollow() throws Exception {
        Follow follow = followService.getFollow(29,31);
        System.out.println(follow);
    }

    @Test
    public void getFollowsByUId() throws Exception {
    }

    @Test
    public void insertFollow() throws Exception {
        Follow follow = new Follow();
        follow.setFromUId(1);
        follow.setToUId(2);
        followService.insertFollow(follow);
    }

    @Test
    public void deleteFollow() throws Exception {
          followService.deleteFollow(1,2);
    }

}