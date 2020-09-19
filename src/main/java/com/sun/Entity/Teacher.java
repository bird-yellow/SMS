package com.sun.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//定义老师
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
        private  Integer id;
        private  String username;
        private  String password;
        private String phone;
        private  Integer cId;
        private  Integer gId;
        private  Integer dpId;
        private  Integer crId;
//        定义班级
        private Clazz clazz;
//        定义年级
        private Grade grade;
//        定义学院
        private Deparment deparment;
//        教师授课课程
        private Course course;

        public Teacher(String username, String password) {
                this.username = username;
                this.password = password;
        }
}
