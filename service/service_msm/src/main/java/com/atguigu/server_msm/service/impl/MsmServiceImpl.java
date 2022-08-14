package com.atguigu.server_msm.service.impl;

import com.atguigu.server_msm.service.MsmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;


@Service
public class MsmServiceImpl implements MsmService {

    @Autowired
    JavaMailSenderImpl mailSender;

    @Override
    public boolean sendMail(String mail,int code) {
        if (StringUtils.isEmpty(mail)) {
            return false;
        } else {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setSubject("小橙子在线学习平台");
            mailMessage.setText("【小橙子在线学习平台】您正在注册验证，验证码" + code + ",本条验证码有效时间为5分钟，如果不是本人操作请忽略此邮件。");
            mailMessage.setFrom("1563325826@qq.com");
            mailMessage.setTo(mail);
            mailSender.send(mailMessage);
            return true;
        }
    }
}
