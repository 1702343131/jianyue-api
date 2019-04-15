package com.soft1721.jianyue.api.service.impl;

import com.soft1721.jianyue.api.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * Created by 张文旭 on 2019/4/15.
 */

@Service("mailService")
public class MailServiceImpl implements MailService {
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendMail(String to, String subject, String content,String filePath) {
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setFrom("473290621@qq.com");//发起者
        mailMessage.setTo(to);//接受者
        mailMessage.setSubject(subject);
        mailMessage.setText(content);
        FileSystemResource file=new FileSystemResource(new File(filePath));
        String fileName=filePath.substring(filePath.lastIndexOf(File.separator));

        try {
            mailSender.send(mailMessage);
            System.out.println("发送简单邮件");
        }catch (Exception e){
            System.out.println("发送简单邮件失败");
        }
    }
}
