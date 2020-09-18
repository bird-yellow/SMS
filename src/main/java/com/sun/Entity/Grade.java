package com.sun.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//定义年级
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Grade {
        private Integer id;
        private String  name;
}
