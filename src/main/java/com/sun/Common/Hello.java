package com.sun.Common;

import org.springframework.stereotype.Component;

@Component
public class Hello {

        @LogAnno(value = "abc")
        public void  say(){
            System.out.println("hello world");
        }
}
