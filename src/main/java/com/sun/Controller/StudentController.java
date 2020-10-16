package com.sun.Controller;

import com.sun.Base.BaseController;
import com.sun.Common.LogAnno;
import com.sun.Entity.Student;
import com.sun.Service.*;
import com.sun.Utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentController extends BaseController {
        @Autowired
        private StudentService studentService;
        @Autowired
        private ClazzService clazzService;
        @Autowired
        private GradeService gradeService;
        @Autowired
        private DeparmentService deparmentService;
        @Autowired
        private TeacherService teacherService;

        @LogAnno(value = "查找学生信息")
        @RequestMapping("/findBySql")
//        多表连接查询
        public String findBySql(Model model,Student student){
            String sql = "select * from student where 1=1 ";
            if(!isEmpty(student.getUsername())){
                sql += " and s_username like '%" + student.getUsername()+"%' ";
            }
            sql += " order by s_id ";
            Pager<Student> pagers = studentService.findBySqlRerturnEntity(sql);
            model.addAttribute("pagers",pagers);
            return "student/student";
        }

        @LogAnno(value="查找某个班级所有学生")
        @RequestMapping("/findBySqlByClazz")
        public String findBySqlByClazz(Integer id,Model model){
                String sql = "select * from student where 1=1 and s_cId="+ id + " order by s_id";
                Pager<Student> pagers = studentService.findBySqlRerturnEntity(sql);
                model.addAttribute("pagers",pagers);
                return "student/student";
        }

        @LogAnno(value = "添加学生")
        @RequestMapping("/add")
        public  String add(Model model){
//            学生选择了老师,(而老师已经确定了 院系,年级,班级)
            String sql = "select * from teacher";
            model.addAttribute("teacher",teacherService.listBySqlReturnEntity(sql));
            return "student/addStudent";
        }

        @RequestMapping("/exAdd")
        public String exAdd(Student student){
                studentService.insert(student);
                return "redirect:/student/findBySql";
        }

        @LogAnno(value = "教师查看学生信息")
        @RequestMapping("/findBySqlByTeacher")
        public  String findBySqlByTeacher(Integer id,Model model){
            String sql = "select * from student where 1=1 and s_tId=" + id + " order by s_id";
            Pager<Student> pagers = studentService.findBySqlRerturnEntity(sql);
            model.addAttribute("pagers",pagers);
            return "student/student";
        }

        @RequestMapping("/load")
        public  void load(){
            String sql = "select * from student where s_id=" +10000010;
            Student student = studentService.load(10000010);
            System.out.println(student);
        }
}
