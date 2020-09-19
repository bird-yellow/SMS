package com.sun.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//定义登陆表单
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginForm {
        private  String username;
        private String password;
        private Integer userType;
}
