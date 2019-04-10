package com.soft1721.jianyue.api.service.impl;

import com.soft1721.jianyue.api.entity.Comment;
import com.soft1721.jianyue.api.entity.vo.CommentVO;
import com.soft1721.jianyue.api.mapper.CommentMapper;
import com.soft1721.jianyue.api.service.CommentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by 张文旭 on 2019/4/8.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentServiceImplTest {
    @Resource
    private CommentService commentService;

    @Test
    public void selectCommentsByAId() throws Exception {
        List<CommentVO> commentVOList= commentService.selectCommentsByAId(1);
        commentVOList.forEach(comment -> System.out.println(comment));
    }

    @Test
    public void addComment() throws Exception {
        Comment comment = new Comment();
        comment.setAId(1);
        comment.setUId(29);
        comment.setContent("哈喽");
        commentService.addComment(comment);
    }
}