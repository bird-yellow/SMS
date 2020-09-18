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
}
