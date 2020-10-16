package com.sun.Entity;

import lombok.Data;

@Data
public class MailSendInfo {
        //发送方地址
        private  String fromAddress;
        //接受方地址
        private  String toAddress;
//        邮件主题
        private  String subject;
//        邮件内容
        private  String content;

}
