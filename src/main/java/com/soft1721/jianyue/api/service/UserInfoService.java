package com.soft1721.jianyue.api.service;

import com.soft1721.jianyue.api.entity.UserInfo;

/**
 * Created by 张文旭 on 2019/4/27.
 */
public interface UserInfoService {
    public UserInfo findByUsername(String username);
}
