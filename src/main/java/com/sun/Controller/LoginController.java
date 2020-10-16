package com.sun.Controller;

import com.sun.Base.BaseController;
import com.sun.Common.LogAnno;
import com.sun.Entity.LoginForm;
import com.sun.Entity.Manage;
import com.sun.Entity.Student;
import com.sun.Entity.Teacher;
import com.sun.Service.ManageService;
import com.sun.Service.StudentService;
import com.sun.Service.TeacherService;
import com.sun.Utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private ManageService manageService;


    @RequestMapping("/uLogin")
    public String uLogin(){
            return "login/uLogin";
    }

    @RequestMapping("/sIndex")
    public String sIndex(){
            return "login/sIndex";
    }
    @RequestMapping("/tIndex")
    public String tIndex(){
        return "login/tIndex";
    }
    @RequestMapping("/mIndex")
    public String mIndex(){
        return "login/mIndex";
    }

    @RequestMapping("/login")
    public String login(LoginForm loginForm, Model model, HttpServletRequest request){
            Integer userType = loginForm.getUserType();
            switch (userType){
                case 1:
                    Student tmp  = new Student(loginForm.getUsername(),loginForm.getPassword());
                    Student student = studentService.getByEntity(tmp);
                    if(student != null){
                        HttpSession session = request.getSession();
                        session.setAttribute(Consts.USER,student);
                        session.setAttribute(Consts.USERNAME,student.getUsername());
                        session.setAttribute(Consts.USERTYPE,1);
                        model.addAttribute("obj",student);
                        return "login/sIndex";
                    }
                    else{
                        return "redirect:/login/uLogin";
                    }
                case 2:
                    Teacher temp = new Teacher(loginForm.getUsername(),loginForm.getPassword());
                        Teacher teacher = teacherService.getByEntity(temp);
                        if(!isEmpty(teacher)){
                                HttpSession session = request.getSession();
                                session.setAttribute(Consts.USERTYPE,2);
                                session.setAttribute(Consts.USER,teacher);
                                session.setAttribute(Consts.USERNAME,teacher.getUsername());
                                model.addAttribute("obj",teacher);
                                return "login/tIndex";
                        }
                        else{
                            return "redirect:/login/uLogin";
                        }
                case 3:
                    Manage temp2 = new Manage(loginForm.getUsername(),loginForm.getPassword());
                    Manage manage =  manageService.getByEntity(temp2);
                    if(!isEmpty(manage)){
                            HttpSession session = request.getSession();
                            session.setAttribute(Consts.USERTYPE,3);
                            session.setAttribute(Consts.USERNAME,manage.getUsername());
                            session.setAttribute(Consts.USER,manage);
                            model.addAttribute("obj",manage);
                            return "login/mIndex";
                    }
                    else{
                        return "redirect:/login/uLogin";
                    }
            }
            return  null;
    }

    @LogAnno(value = "退出")
    @RequestMapping("/logout")
    public  String  logout(HttpServletRequest request){
            request.getSession().invalidate();
            return "redirect:/login/uLogin.action";
    }

}
