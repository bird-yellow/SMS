package com.sun.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.Base.BaseController;
import com.sun.Common.LogAnno;
import com.sun.Entity.Clazz;
import com.sun.Entity.Deparment;
import com.sun.Entity.Grade;
import com.sun.Service.*;
import com.sun.Utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.plugins.jpeg.JPEGImageReadParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/clazz")
public class ClazzController extends BaseController {

    @Autowired
    private ClazzService clazzService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private GradeService gradeService;
    @Autowired
    private DeparmentService deparmentService;


    @LogAnno(value="查找班级信息")
    @RequestMapping("/findBySql")
    public  String findBySql(Model model){
            String sql = "select * from clazz where 1=1 and c_gId is not null and c_dpId is not null ";
            sql += " order by c_id ";
            Pager<Clazz> pagers = clazzService.findBySqlRerturnEntity(sql);
            model.addAttribute("pagers", pagers);
            return "clazz/clazz";
    }


    @RequestMapping("/clazzInfo")
    public String clazzInfo(Clazz clazz,Model model){
            model.addAttribute("obj",clazzService.getByEntity(clazz));
            return "clazz/clazzInfo";
    }

    @LogAnno(value = "添加班级")
    @RequestMapping("/add")
    public  String add(Model model){
            String sql ="select * from grade ";
            List<Grade> gradeList = gradeService.listBySqlReturnEntity(sql);
            String sql2 = "select * from deparment";
            List<Deparment> deparmentList = deparmentService.listBySqlReturnEntity(sql2);
            model.addAttribute("grade",gradeList);
            model.addAttribute("deparment",deparmentList);
            return "clazz/addClazz";
    }

    @RequestMapping("/exAdd")
    public  String exAdd(Clazz clazz){
            clazzService.insert(clazz);
            return "redirect:/clazz/findBySql";
    }

    @LogAnno(value = "修改班级信息")
    @RequestMapping("/updateById")
    public  String updateById(Integer id,Model model){
            Clazz clazz = clazzService.load(id);
            model.addAttribute("obj",clazz);
            return "clazz/updateClazz";
    }

    @RequestMapping("/exUpdate")
    public  String exUpdate(Clazz clazz){
            clazzService.updateById(clazz);
            return "redirect:/clazz/findBySql";
    }

    @LogAnno(value = "删除班级")
    @RequestMapping("/deleteById")
    public String deleteById(Integer  id){
            Clazz clazz = clazzService.load(id);
            clazzService.deleteById(id);
            return "redirect:/clazz/findBySql";
    }


    @LogAnno(value = "查找院系内 班级没有填满的 可选的年级")
    @RequestMapping("/findBySqlByClazzReturnGrade")
    @ResponseBody
    public  String findBySqlByClazzReturnGrade(Integer id) throws JsonProcessingException {
        System.out.println("部门id=" +id);
            String sql = " select g.g_id,g.g_name from (select * from grade where g_dpId is null)g " +
                    " where g.g_id not in (select c_gId from clazz where c_dpId="+id+"  group by c_gId " +
                    " having count(*) = (select count(*) from clazz where c_gId is null and c_dpId is null))";
        System.out.println(gradeService.listBySqlReturnEntity(sql));
        return  new ObjectMapper().writeValueAsString(gradeService.listBySqlReturnEntity(sql));
    }


    @LogAnno(value = "查找部门,年级下 可选的的班级")
    @RequestMapping("/findBySqlByDepar")
    @ResponseBody
    public  String findBySqlByDepar(Integer dpId,Integer gId) throws JsonProcessingException {
            String sql = "select * from (select * from clazz where c_gId is null and c_dpId is null)c " +
                    " where c.c_name not in (select c_name from clazz where c_gId=" +gId + " and c_dpId = "+ dpId +") ";
            return new ObjectMapper().writeValueAsString(clazzService.listBySqlReturnEntity(sql));
    }

    @LogAnno(value = "班级排序")
    @RequestMapping("/findBySqlOrderBy")
    public  String findBySqlOrderBy(Integer type,Model model){
        String sql = "select * from clazz where  c_dpId is not null ";
            switch (type){
                case 1:
                   sql += " order by c_dpId desc";
                   break;
                case 2:
                    sql += " order by c_dpId  ";
                    break;
                case 3:
                    sql += " order by c_gId desc ";
                    break;
                case 4:
                    sql +=  " order by c_gId  ";
                    break;
                case 5:
                    sql +=  " order by c_id desc ";
                    break;
                case 6:
                    sql += "  order by c_id ";
                    break;
            }
            Pager<Clazz> pagers =  clazzService.findBySqlRerturnEntity(sql);
            model.addAttribute("pagers",pagers);
            return "clazz/clazz";
    }
}
