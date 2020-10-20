package com.sun.Controller;

import com.sun.Base.BaseController;
import com.sun.Common.LogAnno;
import com.sun.Entity.Course;
import com.sun.Entity.Student;
import com.sun.Service.*;
import com.sun.Utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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

        @Autowired
        private  CourseService courseService;

        @LogAnno(value = "查找学生信息")
        @RequestMapping("/findBySql")
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


    /**
     * @function: 在学生表中删除学生,在课程中删除这个学生对应的课程
     * @param id
     * @return
     */
    @RequestMapping("/deleteById")
        public  String deleteById(Integer id){
                Student student = studentService.load(id);
                List<Course> lists = student.getCourseList();
                System.out.println("学生所学课程="+ lists);
                studentService.deleteById(id);
                courseService.deleteByStudentId(id);
                return "redirect:/student/findBySql";
        }

}
