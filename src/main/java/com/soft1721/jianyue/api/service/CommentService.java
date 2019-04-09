package com.soft1721.jianyue.api.service;

import com.soft1721.jianyue.api.entity.vo.CommentVO;

import java.util.List;

/**
 * Created by 张文旭 on 2019/4/8.
 */
public interface CommentService {
    List<CommentVO> selectCommentsByAId(int aId);
}
