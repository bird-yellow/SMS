package com.sun.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//定义课程
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {

//        主键
        private Integer  id;

        private String  name;

//        课时
        private String time;

//        学生id;如果一个课程没有学生id, 那么这个课程是学校开放的
        private Integer sid;

        //学生课程分数
        private Integer score;
}
