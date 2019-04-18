package com.soft1721.jianyue.api.service;

import com.soft1721.jianyue.api.entity.Article;
import com.soft1721.jianyue.api.entity.vo.ArticleVO;

import java.util.List;

/**
 * Created by 张文旭 on 2019/4/8.
 */
public interface ArticleService {
    //获取所有文章
    List<ArticleVO> selectAll();
    //根据文章id获取文章
    ArticleVO getArticleById(int aId);
    //新增文章
    void insertArticle(Article article);
  //根于用户查文章
  List<ArticleVO>getArticleByuId(int uId);
  //根据文章id删除文章
    void deleteArticle(int id);

}
