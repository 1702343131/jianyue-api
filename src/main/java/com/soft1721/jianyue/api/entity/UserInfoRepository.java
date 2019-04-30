package com.soft1721.jianyue.api.entity;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by 张文旭 on 2019/4/27.
 */
public interface UserInfoRepository extends CrudRepository<UserInfo, Long> {

    public UserInfo findByUsername(String username);

    public UserInfo save(UserInfo userInfo);
}

