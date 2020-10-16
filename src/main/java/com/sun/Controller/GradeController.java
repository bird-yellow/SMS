package com.sun.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.Base.BaseController;
import com.sun.Common.LogAnno;
import com.sun.Entity.Deparment;
import com.sun.Entity.Grade;
import com.sun.Service.DeparmentService;
import com.sun.Service.GradeService;
import com.sun.Utils.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/grade")
public class GradeController extends BaseController {
        @Autowired
        private GradeService gradeService;

        @Autowired
        private DeparmentService deparmentService;

        @LogAnno(value = "查找所有年级")
        @RequestMapping("/findBySql")
        public  String findBySql(Model model,Grade grade){
                String sql = "select * from grade where 1=1 and g_dpId is not  null ";
                if(!isEmpty(grade.getName())){
                        sql += " and g_name like '%" + grade.getName() + "%' ";
                }
                sql += " order by g_id";
                Pager<Grade> pagers = gradeService.findBySqlRerturnEntity(sql);
                model.addAttribute("pagers",pagers);
                model.addAttribute("obj",grade);
                return "grade/grade";
        }

        @RequestMapping("/deleteById")
        //删除年级下的所有班级,班级下面的所有学生,班级下的老师
        public  String deleteById(Integer id){
                Grade grade = gradeService.load(id);
                if(!isEmpty(grade)){
                        gradeService.deleteById(id);
                }
                return "redirect:/grade/findBySql";
        }

        @RequestMapping("/add")
        public String add(Model model){
                String sql = "select * from deparment ";
                model.addAttribute("deparment",deparmentService.listBySqlReturnEntity(sql));
                return "grade/add";
        }

        @RequestMapping("/exAdd")
        public  String exAdd(Grade grade){
                gradeService.insert(grade);
                return "redirect:/grade/findBySql";
        }

        @RequestMapping("/findBySqlByDepar")
        @ResponseBody
        //根据院系内 还没有选定的年级.
        public  String findBySqlByDepar(Integer id) throws JsonProcessingException {
                String sql = "select g.g_id,g.g_name from (select * from grade where g_dpId is null) g where g.g_name not in (select g_name from grade where g_dpId = " + id +")";
                return new ObjectMapper().writeValueAsString(gradeService.listBySqlReturnEntity(sql));
        }

        @RequestMapping("/OrderByGrade")
        public  String orderByGrade(Integer type,Model model){
                String sql = "select * from grade ";
                switch (type){
                        case 1:
                                sql += " order by g_id ";
                                break;
                        case 2:
                                sql += " order by g_id desc ";
                                break;
                        case 3:
                                sql += " order by g_dpId ";
                                break;
                        case 4:
                                sql += " order by g_dpId desc ";
                                break;
                }
                Pager<Grade> pagers = gradeService.findBySqlRerturnEntity(sql);
                model.addAttribute("pagers",pagers);
                return "grade/grade";
        }

}
