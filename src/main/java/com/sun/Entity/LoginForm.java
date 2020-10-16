package com.sun.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

//定义登陆表单
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginForm implements Serializable {
        private  String username;
        private String password;
        private Integer userType;
}
