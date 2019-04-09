package com.soft1721.jianyue.api.mapper;

import com.soft1721.jianyue.api.entity.Img;
import net.bytebuddy.asm.Advice;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by 张文旭 on 2019/4/8.
 */
public interface ImgMapper {
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "aId",property = "a_id"),
            @Result(column = "img_url",property = "imgUrl")
    })
    @Select("SELECT * FROM t_img WHERE a_id = #{aId} ")
    List<Img> selectImgsByAId(int aId);
}
