package com.sun.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

//定义日志
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Log  implements Serializable {
//        主键
        private  Integer id;
//        用户名
        private  String username;
//        用户执行的操作
        private  String operator ;
//        用户操作时间
        private  String time;
//        用户操作结果
        private  String result ;
//        用户操作的IP地址
        private  String ip;
}
