package com.soft1721.jianyue.api.service.impl;

import com.soft1721.jianyue.api.entity.UserInfo;
import com.soft1721.jianyue.api.entity.UserInfoRepository;
import com.soft1721.jianyue.api.service.UserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by 张文旭 on 2019/4/27.
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoRepository userInfoRepository;

    @Override
    public UserInfo findByUsername(String username) {

        System.out.println("UserInfoServiceImpl.findByUsername");
        return userInfoRepository.findByUsername(username);
    }
}