package com.soft1721.jianyue.api.service.impl;

import com.soft1721.jianyue.api.entity.Img;
import com.soft1721.jianyue.api.mapper.ImgMapper;
import com.soft1721.jianyue.api.service.ImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 张文旭 on 2019/4/8.
 */
@Service
public class ImgServiceImpl implements ImgService {
     @Resource
     private ImgMapper imgMapper;
    @Override
    public List<Img> selectImgsByAId(int aId) {
        return imgMapper.selectImgsByAId(aId);
    }

    @Override
    public void insertImg(Img img) {
        imgMapper.insertImg(img);
    }
}
