package com.soft1721.jianyue.api.controller;

import com.github.pagehelper.PageHelper;
import com.soft1721.jianyue.api.entity.Article;
import com.soft1721.jianyue.api.entity.Follow;
import com.soft1721.jianyue.api.entity.Like;
import com.soft1721.jianyue.api.entity.vo.ArticleVO;
import com.soft1721.jianyue.api.entity.vo.CommentVO;
import com.soft1721.jianyue.api.service.*;
import com.soft1721.jianyue.api.util.MsgConst;
import com.soft1721.jianyue.api.util.ResponseResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 张文旭 on 2019/4/8.
 */
@RestController
@RequestMapping(value = "/api/article")
public class ArticleController {
    @Resource
    private ArticleService articleService;
    @Resource
    private CommentService commentService;
    @Resource
    private ImgService imgService;
    @Resource
    private FollowService followService;
    @Resource
    private LikeService likeService;
    @GetMapping(value = "/list")
    public ResponseResult getAll() {
        List<ArticleVO> articleList = articleService.selectAll();
        return ResponseResult.success(articleList);
    }

    @GetMapping(value = "/{aId}")
    public ResponseResult getArticleById(@PathVariable("aId") int aId,@RequestParam("userId") int userId) {
        ArticleVO article = articleService.getArticleById(aId);
        int toUId = article.getUId();
        int toAId=article.getId();

        Map<String, Object> map = new HashMap<>();
        Follow follow = followService.getFollow(userId, toUId);
        if (follow != null) {
            map.put("followed", MsgConst.FOLLOWED);
        } else {
            map.put("followed", MsgConst.NO_FOLLOWED);
        }
        Like like = likeService.getLike(userId, toAId);
        if (like != null) {
            map.put("Likeed", MsgConst.LIKEED);
        } else {
            map.put("Likeed", MsgConst.NO_LIKEED);
        }

        List<CommentVO> comments = commentService.selectCommentsByAId(aId);
        map.put("article", article);
        map.put("comments", comments);
        return ResponseResult.success(map);
    }
    @PostMapping("/add")
    public ResponseResult postArticle(@RequestParam("uId") int uId,
                                      @RequestParam("title") String title,
                                      @RequestParam("content") String content) {
        Article article = new Article();
        article.setUId(uId);
        article.setTitle(title);
        article.setContent(content);
        article.setCreateTime(new Date());
        articleService.insertArticle(article);
        //新增文章后，将获取到的自增主键返回给客户端，方便图片地址的写入
        return ResponseResult.success(article.getId());
    }
    @GetMapping("/user")
    public ResponseResult getArticleByuId (@RequestParam("uId") int uId) {
        List<ArticleVO> articleVOList=articleService.getArticleByuId(uId);
        return  ResponseResult.success(articleVOList);
    }
    @GetMapping("/delete/{id}")
    public ResponseResult deleteArticle(@PathVariable("id") int id){
        articleService.deleteArticle(id);
        return ResponseResult.success();
    }
    @GetMapping("/selectAll")
    public List<ArticleVO> selectAll(int page, int size) {
        //第一参数：第几页。 第二参数：每页几条。基于拦截器模式直接使用即可。
        PageHelper.startPage(page,size);
        return articleService.selectAll();
    }
}