package com.sun.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
//定义管理员
public class Manage implements Serializable {
//        主键
        private Integer id;
//        管理员名字
        private String username;
        private String password;
        private  String email;

        public Manage(String username, String password) {
                this.username = username;
                this.password = password;
        }
}

