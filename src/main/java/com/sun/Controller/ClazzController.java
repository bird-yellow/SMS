package com.sun.Controller;

import com.sun.Base.BaseController;
import com.sun.Entity.Clazz;
import com.sun.Service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/clazz")
public class ClazzController extends BaseController {

    @Autowired
    private ClazzService clazzService;

    @RequestMapping("/findBySql")
    public  String findBySql(Model model, Clazz clazz){
            String sql = "select * from clazz where 1=1 ";
            if(!isEmpty(clazz.getName())){
                    sql += " and name like '%" + clazz.getName() + "%' ";
            }
            sql += " order by id ";
            model.addAttribute("lists",clazzService.findBySqlRerturnEntity(sql));
            model.addAttribute("obj",clazz);
            return "clazz/clazz";
    }

}
