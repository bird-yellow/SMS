package com.sun.Mail;


import com.sun.Entity.MailSendInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/*
* @Descritpion : 定义发送html消息的模板
* @Param :mailSendInfo 传递用户信息.发送内容，邮箱地址
* */
@Component
public class HtmlMessage implements MessageType {
    /**
     * 使用spring进行依赖注入:JavaMailSender
     * */
    @Autowired
    private  JavaMailSender javaMailSender;

    @Override
    public void printMessage(MailSendInfo mailSendInfo) {
//        ApplicationContext factory =new  ClassPathXmlApplicationContext("applicationMail.xml");
//        JavaMailSender javaMailSender =(JavaMailSender) factory.getBean("mailSender");
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            mimeMessage.setSubject("发送验证码");
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true,"UTF-8");
            helper.setFrom("15210815016@163.com");
            helper.setTo(mailSendInfo.getToAddress());
                MimeMultipart multipart  = new MimeMultipart();
                MimeBodyPart bodyPart  = new MimeBodyPart();
                StringBuffer sb = new StringBuffer();
                sb.append("<html><head><title>修改密码的验证码</title></head><body><h1>" + mailSendInfo.getContent() + "</h1></body></html>");
                bodyPart.setContent(sb.toString(),"text/html;charset=UTF-8");
                multipart.addBodyPart(bodyPart);
            mimeMessage.setContent(multipart);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
