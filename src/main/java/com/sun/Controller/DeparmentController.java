package com.sun.Controller;

import com.sun.Base.BaseController;
import com.sun.Common.LogAnno;
import com.sun.Entity.Clazz;
import com.sun.Entity.Deparment;
import com.sun.Entity.Grade;
import com.sun.Service.ClazzService;
import com.sun.Service.DeparmentService;
import com.sun.Service.GradeService;
import com.sun.Service.TeacherService;
import com.sun.Utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/deparment")
public class DeparmentController extends BaseController {
        @Autowired
        private DeparmentService deparmentService;

        @Autowired
        private GradeService gradeService;

        @Autowired
        private ClazzService clazzService;

        @Autowired
        private TeacherService teacherService;

        @LogAnno(value = "查找所有院系")
        @RequestMapping("/findBySql")
        public String findBySql(Deparment deparment, Model model){
                String sql = "select * from deparment where 1=1 ";
                if(!isEmpty(deparment.getName())){
                        sql +=  " and dp_name like '%" + deparment.getName() + "%' ";
                }
                sql += " order by dp_id ";
                Pager<Deparment> pagers = deparmentService.findBySqlRerturnEntity(sql);
                model.addAttribute("pagers",pagers);
                model.addAttribute("obj",deparment);
                return "deparment/deparment";
        }

        @LogAnno(value = "增加院系")
        @RequestMapping("/add")
        public  String add(Model model){
                String sql = "select * from deparment ";
                model.addAttribute("deparment",deparmentService.listBySqlReturnEntity(sql));
                return "deparment/addDeparment";
        }

        @RequestMapping("/exAdd")
        public  String exAdd(Deparment deparment){
                deparmentService.insert(deparment);
                return "redirect:/deparment/findBySql";
        }

        @LogAnno(value = "对院系进行排序")
        @RequestMapping("/findBySqlOrderBy")
        public  String findBySqlOrderBy(Integer type,Model model){
                String sql = "select * from deparment ";
                switch (type){
                        case 1:
                                sql += " order by dp_id ";
                                break;
                        case 2:
                                sql += " order by dp_id desc ";
                                break;
                }
                Pager<Deparment> pagers = deparmentService.findBySqlRerturnEntity(sql);
                model.addAttribute("pagers",pagers);
                return "deparment/deparment";
        }

        @LogAnno(value = "删除院系:包括年级,班级,教师,学生")
        @RequestMapping("/deleteById")
        public  String deleteById(Integer id){
                deparmentService.deleteById(id);
                return "redirect:/deparment/findBySql";
        }

        @LogAnno(value = "查询院系中所有年级")
        @RequestMapping("/findBySqlGetGrade")
        public  String findBySqlGetGrade(Integer id,Model model){
                String sql= "select * from (select * from grade where g_dpId is not null)g where g.g_dpId =" + id + " order by g_id";
                Pager<Grade> pagers = gradeService.findBySqlRerturnEntity(sql);
                return "grade/grade";
        }

        @LogAnno(value="查询院系中所有班级")
        @RequestMapping("/findBySqlGetClazz")
        public  String findBySqlGetClazz(Integer id,Model model){
                String sql = "select * from (select * from clazz where c_dpId is not null and c_gId is not null)c " +
                        " where c.c_dpId ="+id + " order by c_id";
                Pager<Clazz> pagers =  clazzService.findBySqlRerturnEntity(sql);
                model.addAttribute("pagers",pagers);
                return "clazz/clazz";
        }

        @LogAnno(value="查询院系中所有教师")
        @RequestMapping("/findBySqlGetTeacher")
        public  String findBySqlGetTeacher(Integer id,Model model){
                return null;
        }
}
