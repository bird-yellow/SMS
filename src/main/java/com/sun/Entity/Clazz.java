package com.sun.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

//定义班级
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Clazz implements  Serializable{
        private  Integer id;
        private  String name;
        private  Integer gId;
        private  Integer dpId;

        private  Grade grade;
        private  Deparment deparment;
}
