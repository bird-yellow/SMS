package com.sun.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

//定义课程
/*
* @Description : 课程大致分为三类:1)学校所有课程 2)教师教授课程 3)学生选择课程
* 查找学校课程:select * from (select * from course where cr_sId is null) cr;
* */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course implements Serializable {

//        主键
        private Integer  id;

        private String  name;

//        课时
        private String time;

//        学生id;如果一个课程没有学生id, 那么这个课程是学校开放的
        private Integer sid;

        //院系id
        private Integer dpId;

        //学生课程分数
        private Integer score;

        //返回院系
        private Deparment deparment;

}
