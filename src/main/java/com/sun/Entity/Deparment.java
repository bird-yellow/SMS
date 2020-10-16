package com.sun.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

//定义部门
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Deparment implements Serializable {
        private Integer id;
        private String name;
}
