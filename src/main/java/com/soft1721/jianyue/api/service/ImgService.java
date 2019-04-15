package com.soft1721.jianyue.api.service;

import com.soft1721.jianyue.api.entity.Img;

import java.util.List;

/**
 * Created by 张文旭 on 2019/4/8.
 */
public interface ImgService {
    //根据文章id查找图片
    List<Img> selectImgsByAId(int aId);
//    添加图片
    void insertImg(Img img);
}
