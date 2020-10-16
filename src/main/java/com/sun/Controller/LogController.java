package com.sun.Controller;

import com.sun.Base.BaseController;
import com.sun.Common.LogAnno;
import com.sun.Entity.Log;
import com.sun.Service.LogService;
import com.sun.Utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/log")
public class LogController extends BaseController {
        @Autowired
        private LogService logService;

        @RequestMapping("/findBySql")
        public  String getAll(Model model, Log log){
                String sql = "select * from log  where 1=1 ";
                if(!isEmpty(log.getUsername())){
                        sql += " ane username like '%" + log.getUsername()+"%' ";
                }
                sql += "order by l_id";
                Pager<Log> pagers = logService.findBySqlRerturnEntity(sql);
                model.addAttribute("pagers",pagers);
                model.addAttribute("obj",log);
                return "log/log";
        }

        @RequestMapping("/deleteById")
        public  String deleteById(Integer id){
                Log log = logService.load(id);
                logService.deleteById(id);
                return "redirect:/log/findBySql";
        }

        @LogAnno(value="日志删除")
        @RequestMapping("/clear")
        public String clear(Integer value){
                switch (value){
                        case 1:
                                //因为最多保留100天内的日志
                                logService.clear(1);
                                break;
                        case 2:
                                logService.clear(7);
                                break;
                        case 3:
                                logService.clear(30);
                                break;
                        case 4:
                                logService.clear(0);
                                break;
                }
                return "redirect:/log/findBySql";
        }
}
