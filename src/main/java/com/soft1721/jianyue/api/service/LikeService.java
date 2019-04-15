package com.soft1721.jianyue.api.service;

import com.soft1721.jianyue.api.entity.Follow;
import com.soft1721.jianyue.api.entity.Like;
import com.soft1721.jianyue.api.entity.vo.LikeVO;

import java.util.List;

/**
 * Created by 张文旭 on 2019/4/12.
 */
public interface LikeService {
    //获取关注与被关注id
    Like getLike(int fromUId, int toAId);
    //所关注的人
    List<LikeVO> getLikesByUId(int fromUId);
    //关注
    void insertLike(Like like);
    //取消关注
    void deleteLike(int fromUId, int toAId);
}
