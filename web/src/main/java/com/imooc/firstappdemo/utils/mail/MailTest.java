package com.imooc.firstappdemo.utils.mail;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
public class MailTest {
    @Autowired
    private JavaMailSender mailSender;

    /**
     * 修改application.properties的用户，才能发送。
     */
    @RequestMapping("/postMail")
    public String sendSimpleEmail(){
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("872646489@qq.com");//发送者
        message.setTo("1298629573@qq.com");//接收者
        message.setSubject("测试邮件（邮件主题）");//邮件主题
        message.setText("邮件内容");//邮件内容

        mailSender.send(message);//发送邮件
        return JSON.toJSONString(message);
    }

}
