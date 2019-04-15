package com.soft1721.jianyue.api.mapper;

import com.soft1721.jianyue.api.entity.Article;
import com.soft1721.jianyue.api.entity.vo.ArticleVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by 张文旭 on 2019/4/8.
 */
public interface ArticleMapper {
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "u_id",property = "uId"),
            @Result(column = "title",property = "title"),
            @Result(column = "content",property = "content"),
            @Result(column = "create_time",property = "createTime"),
            @Result(column = "nickname",property = "nickname"),
            @Result(column = "avatar",property = "avatar")
    })
   @Select("SELECT a.*,b.id,b.nickname,b.avatar FROM t_article a LEFT JOIN t_user b ON a.u_id=b.id ORDER By b.id DESC ")
    List<ArticleVO> selectAll();

    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "u_id",property = "uId"),
            @Result(column = "title",property = "title"),
            @Result(column = "content",property = "content"),
            @Result(column = "create_time",property = "createTime"),
            @Result(column = "nickname",property = "nickname"),
            @Result(column = "avatar",property = "avatar")
    })
    @Select("SELECT a.*,b.id,b.nickname,b.avatar FROM t_article a LEFT JOIN t_user b ON a.u_id=b.id WHERE a.id = #{id} ")
    ArticleVO getArticleById(int aId);

    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "u_id",property = "uId"),
            @Result(column = "title",property = "title"),
            @Result(column = "content",property = "content"),
            @Result(column = "create_time",property = "createTime"),
            @Result(column = "nickname",property = "nickname"),
            @Result(column = "avatar",property = "avatar")
    })
    @Insert("INSERT INTO t_article (u_id,title,content,create_time) VALUES (#{uId},#{title},#{content},#{createTime}) ")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void insertArticle(Article article);

    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "u_id",property = "uId"),
            @Result(column = "title",property = "title"),
            @Result(column = "content",property = "content"),
            @Result(column = "create_time",property = "createTime"),
            @Result(column = "nickname",property = "nickname"),
            @Result(column = "avatar",property = "avatar")
    })
    @Select("SELECT a.title,a.id,a.u_id,b.id FROM t_article a LEFT JOIN t_user b ON a.u_id=b.id WHERE b.id = #{id}")
    List<ArticleVO>getArticleByuId(int uId);


 @Results({
         @Result(column = "id",property = "id"),
         @Result(column = "u_id",property = "uId"),
         @Result(column = "title",property = "title"),
         @Result(column = "content",property = "content"),
         @Result(column = "create_time",property = "createTime"),
         @Result(column = "nickname",property = "nickname"),
         @Result(column = "avatar",property = "avatar")
 })
 @Delete("DELETE FROM t_article WHERE id = #{id}")
 void deteleArticle(int id);
}
