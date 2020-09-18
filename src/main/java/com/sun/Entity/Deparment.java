package com.sun.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//定义部门
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Deparment {
        private Integer id;
        private String name;
}
