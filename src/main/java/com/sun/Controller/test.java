package com.sun.Controller;

import com.sun.Common.LogAnno;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class test {

        @LogAnno(value = "abc")
        @RequestMapping("abc")
        public void abc(){
            System.out.println("测试成功");
        }
}
