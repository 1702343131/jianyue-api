package com.soft1721.jianyue.api.service;

/**
 * Created by 张文旭 on 2019/4/15.
 */
public interface MailService {
    void sendMail(String to,String subject,String content,String filePath);
}
