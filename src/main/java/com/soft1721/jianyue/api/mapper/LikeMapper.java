package com.soft1721.jianyue.api.mapper;

import com.soft1721.jianyue.api.entity.Follow;
import com.soft1721.jianyue.api.entity.Like;
import com.soft1721.jianyue.api.entity.vo.LikeVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by 张文旭 on 2019/4/12.
 */
public interface LikeMapper {
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "fromUId", column = "from_uid"),
            @Result(property = "toAId", column = "to_aid")
    })
    @Select("SELECT * FROM t_like WHERE from_uid = #{fromUId} AND to_aid = #{toAId} ")
    Like getLike(@Param("fromUId") int fromUId, @Param("toAId") int toAId);

    @Results({
            @Result(property = "toAId", column = "to_aid"),
            @Result(property = "nickname", column = "nickname"),
            @Result(property = "avatar", column = "avatar")
    })
    @Select("SELECT a.to_aid,b.id,b.title,b.create_time FROM t_like a LEFT JOIN t_article b ON a.to_aid = b.id WHERE a.from_uid = #{fromUId}  ")
    List<LikeVO> getLikesByUId(int fromUId);

    @Insert("INSERT INTO t_like (from_uid,to_aid) VALUES (#{fromUId},#{toAId}) ")
    void insertLike(Like like);

    @Delete("DELETE  FROM t_like WHERE from_uid = #{fromUId} AND to_aid = #{toAId} ")
    void deleteLike(@Param("fromUId") int fromUId, @Param("toAId") int toAId);
}

