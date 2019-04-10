package com.soft1721.jianyue.api.service;

import com.soft1721.jianyue.api.entity.Follow;
import com.soft1721.jianyue.api.entity.vo.FollowVO;

import java.util.List;

/**
 * Created by 张文旭 on 2019/4/10.
 */
public interface FollowService {
    //获取关注与被关注id
    Follow getFollow(int fromUId, int toUId);
   //所关注的人
    List<FollowVO> getFollowsByUId(int fromUId);
    //关注
    void insertFollow(Follow follow);
//取消关注
    void deleteFollow(int fromUId, int toUId);
}
