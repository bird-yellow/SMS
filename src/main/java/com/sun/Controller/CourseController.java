package com.sun.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.Base.BaseController;
import com.sun.Common.LogAnno;
import com.sun.Entity.Course;
import com.sun.Entity.Deparment;
import com.sun.Service.CourseService;
import com.sun.Service.DeparmentService;
import com.sun.Utils.Pager;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jws.WebParam;
import java.util.List;


@RequestMapping("/course")
@Controller
public class CourseController extends BaseController {
        @Autowired
        private CourseService courseService;
        @Autowired
        private DeparmentService deparmentService;

        @LogAnno(value = "查找所有课程")
        @RequestMapping("/findBySql")
//        查找课程的学生编号为null 的课程(查找原子课程)
        public  String findBySql(Model model, Course course){
                String sql = "select * from course where cr_sId is null and 1=1";
                if(!isEmpty(course.getName())){
                        sql += " and cr_name like '%" + course.getName() + "%' " ;
                }
                sql += " order by cr_id";
                Pager<Course> pagers = courseService.findBySqlRerturnEntity(sql);
                model.addAttribute("pagers",pagers);
                model.addAttribute("obj",course);
                return "course/course";
        }

        @RequestMapping("/findByStudentId")
        public String findByStudentId(Integer id, Model model){
                String sql = "select * from course where cr_sId=" +id + " order by cr_id";
                Pager<Course> pagers = courseService.findBySqlRerturnEntity(sql);
                model.addAttribute("pagers",pagers);
                return "course/studentCourse";
        }


        @RequestMapping("/add")
        public  String add(Model model){
                String sql = "select * from deparment ";
                List<Deparment> deparments = deparmentService.listBySqlReturnEntity(sql);
                model.addAttribute("deparments",deparments);
                return "course/addCourse";
        }

        /**
         * 课程添加 程序,还缺少验证的功能. 不能让课程之间有重复
         * @param course
         * @return
         */
        @RequestMapping("/exAdd")
        public  String exAdd(Course course){
                courseService.insert(course);
                return "redirect:/course/findBySql";
        }

        /**
         * function : 公开课是属于学校的
         * @return
         */
        @LogAnno(value = "添加公开课")
        @RequestMapping("/addOpen")
        public  String addOpen(){
                return "course/addOpenCourse";
        }

        /**
         * 缺少验证功能
         * @param course
         * @return
         */
        public  String exAddOpen(Course course){
                courseService.insert(course);
                return "redirect:/course/findBySql";
        }

        @LogAnno(value = "删除课程")
        @RequestMapping("/deleteById")
        public  String deleteById(Integer id){
                Course course = courseService.load(id);
                courseService.deleteById(id);
                return "redirect:/course/findBySql";
        }

        @LogAnno(value="修改课程")
        @RequestMapping("/updateById")
        public  String updateById(Integer id,Integer type){
                switch (type){
                        case 1:
                                return "course/updateCourse1";
                        case 2:
                                return "course/updateCourse2";
                }
                return null ;
        }
}
