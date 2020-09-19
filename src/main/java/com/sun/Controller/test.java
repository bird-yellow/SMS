package com.sun.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class test {
        @RequestMapping("abc")
        public String abc(){
            return "login/main";
        }
}
