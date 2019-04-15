package com.soft1721.jianyue.api.service.impl;

import com.soft1721.jianyue.api.entity.Like;
import com.soft1721.jianyue.api.entity.vo.LikeVO;
import com.soft1721.jianyue.api.mapper.LikeMapper;
import com.soft1721.jianyue.api.service.LikeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 张文旭 on 2019/4/12.
 */
@Service
public class LikeServiceImpl implements LikeService {
    @Resource
    private LikeMapper likeMapper;
    @Override
    public Like getLike(int fromUId, int toAId) {
        return likeMapper.getLike(fromUId,toAId);
    }

    @Override
    public List<LikeVO> getLikesByUId(int fromUId) {
        return likeMapper.getLikesByUId(fromUId);
    }

    @Override
    public void insertLike(Like like) {
         likeMapper.insertLike(like);
    }

    @Override
    public void deleteLike(int fromUId, int toAId) {
           likeMapper.deleteLike(fromUId,toAId);
    }
}
