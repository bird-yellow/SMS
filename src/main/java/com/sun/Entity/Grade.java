package com.sun.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

//定义年级
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Grade implements Serializable {
        private Integer id;
        private String  name;
        private  Integer dpId;

        private  Deparment deparment;
}
