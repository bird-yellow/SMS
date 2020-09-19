package com.sun.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


//定义学生
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
        private  Integer id;
        private String username;
        private String password;
        private String sex;
        private String phone;
//        民族
        private String nation;
//        籍贯
        private String nativePlace;
        private Integer cId;
        private Integer gId;
        private  Integer tId;
        private  Integer dpId;

        private Clazz clazz;
        private Grade grade;
        private Teacher teacher;
        private Deparment deparment;
        private List<Course> courseList;

        public Student(String username, String password) {
                this.username = username;
                this.password = password;
        }
}
