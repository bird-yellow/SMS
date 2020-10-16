package com.sun.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.Base.BaseController;
import com.sun.Common.LogAnno;
import com.sun.Entity.Teacher;
import com.sun.Service.*;
import com.sun.Utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/teacher")
public class TeacherController extends BaseController {
        @Autowired
        private TeacherService teacherService;
        @Autowired
        private GradeService gradeService;
        @Autowired
        private DeparmentService deparmentService;
    @Autowired
        private ClazzService clazzService;
    @Autowired
        private CourseService courseService;

        @RequestMapping("/findBySql")
        public String findBySql(Model model,Teacher teacher){
                String sql =  "select  * from  teacher where 1=1 " ;
                if(!isEmpty(teacher.getUsername())){
                    sql += " and t_username like '%" + teacher.getUsername() + " % '";
                }
                sql += " order by t_id";
            Pager<Teacher> pagers = teacherService.findBySqlRerturnEntity(sql);
            model.addAttribute("pagers",pagers);
            model.addAttribute("obj",teacher);
            return "teacher/teacher";
        }


        @LogAnno(value = "获取教师信息")
        @RequestMapping("/teacherInfo")
        public  String teacherInfo(Integer id,Model model){
                String sql = "select  * from teacher where t_cId=" + id + " order by t_id";
                Pager<Teacher> pagers = teacherService.findBySqlRerturnEntity(sql);
                model.addAttribute("pagers",pagers);
                return "teacher/teacherInfo";
        }

        @LogAnno(value = "增加教师")
        @RequestMapping("/add")
        public String add(Model model){
//            查询教师的班级,年级,课程,部门.一个年级只能由四个班级.;一个部门只能有四个年级
            String sql = "select * from grade where g_dpId is null ";
            model.addAttribute("grade",gradeService.listBySqlReturnEntity(sql));

            String sql2 = "select  * from deparment ";
            model.addAttribute("deparment",deparmentService.listBySqlReturnEntity(sql2));

            String sql3 = "select * from clazz where c_gId is null and c_dpId is null";
            model.addAttribute("clazz",clazzService.listBySqlReturnEntity(sql3));

            String sql4 = "select * from course";
            model.addAttribute("course",courseService.listBySqlReturnEntity(sql4));
            return "teacher/addTeacher";
        }

        @RequestMapping("/exAdd")
        public  String exAdd(Teacher teacher){
                teacherService.insert(teacher);
                return "redirect:/teacher/findBySql";
        }

        @RequestMapping("/load")
        @ResponseBody
        public  String load(Integer id) throws JsonProcessingException {
                String sql = "select * from teacher where t_id=" + id;
                Teacher teacher = teacherService.load(id);
                return new ObjectMapper().writeValueAsString(teacher);
        }

        @RequestMapping("/deleteById")
        public  String deleteById(Integer id){
                Teacher teacher = teacherService.load(id);
            System.out.println(teacher);
                if(!isEmpty(teacher)){
                        teacherService.deleteById(id);
                }
                return "redirect:/teacher/findBySql";
        }

        /*
        *@function: 教师查找可选的年级
        *@Description:查找部门中班级没有选定的年级. 从teacher 表查找
        * @params :部门id
         */
        @RequestMapping("/findBySqlByClazzReturnGrade")
        @ResponseBody
        public  String findBySqlBuClazzReturnGrade(Integer id) throws JsonProcessingException {
            String sql = " select g.g_id,g.g_name from (select * from grade where g_dpId is null)g " +
                    " where g.g_id not in (select t_gId from teacher where t_dpId="+id+"  group by t_gId " +
                    " having count(*) = (select count(*) from clazz where c_gId is null and c_dpId is null))";
            return new ObjectMapper().writeValueAsString(gradeService.listBySqlReturnEntity(sql));
        }

        /*
        *@function : 教师查找可选的班级
        *@Description: 查找部门 和 年级下 可选的的班级.
         */
        @RequestMapping("/findBySqlByDepar")
        @ResponseBody
        public  String findBySqlByDepar(Integer dpId,Integer gId) throws JsonProcessingException {
            String sql = "select * from (select * from  clazz  where c_gId is null and c_dpId is null)c " +
                    " where c.c_name not in (select clazz.c_name from teacher,clazz  where clazz.c_id = teacher.t_cId and teacher.t_gId=" +gId + " and teacher.t_dpId = "+ dpId +") ";
            return new ObjectMapper().writeValueAsString(clazzService.listBySqlReturnEntity(sql));
        }

        /*
        * @function: 根据院系id查找院系下面的公开课程
        * @param :院系id
        * @Descritpion : 院系下的每个课程最多只能有三个教师 教授.(在教师表中查找 t_crId )
        * */
        @RequestMapping("/getCourseByDeparmentId")
        @ResponseBody
        public  String getCourseByDeparmentId(Integer id) throws JsonProcessingException {
            String sql = "select * from (select * from course where cr_sId is null ) cr " +
                    " where cr.cr_id not in (select  t_crId from teacher group by t_crId having count(*) >=3 ) and cr.cr_dpId =" +id;
            return new ObjectMapper().writeValueAsString(courseService.listBySqlReturnEntity(sql));
        }
}
