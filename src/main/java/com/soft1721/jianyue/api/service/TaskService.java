package com.soft1721.jianyue.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Created by 张文旭 on 2019/4/15.
 */
@Service
    public class TaskService {
        @Autowired
        private MailService mailService;

        @Scheduled(cron = "0 27 10 ? * *")
        public void proces(){
            mailService.sendMail("473290621@qq.com","张文旭","9.53","D:\\log.jpg");
            System.out.println("111");
        }
    }

