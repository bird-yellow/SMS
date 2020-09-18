package com.sun.Controller;

import com.sun.Entity.Clazz;
import com.sun.Service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.swing.text.html.parser.Entity;

@Controller
@RequestMapping("/test")
public class ClazzTest {
        @Autowired
        private ClazzService clazzService;


        @RequestMapping("/deleteById")
        public  String  deleteById(Integer id){
            clazzService.deleteById(id);
            System.out.println("删除成功");
            return "test";
        }
        @RequestMapping("/deleteByEntity")
        public String deleteByEntity(Clazz clazz){
            System.out.println(clazz);
                clazzService.deleteByEntity(clazz);
            System.out.println("删除成功");
            return "test";
        }
}
