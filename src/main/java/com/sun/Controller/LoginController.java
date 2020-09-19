package com.sun.Controller;

import com.sun.Base.BaseController;
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
                        session.setAttribute(Consts.STUDENT,student);
                        session.setAttribute(Consts.USERTYPE,1);
                        model.addAttribute("obj",student);
                        return "login/sIndex";
                    }
                        break;
                case 2:
                    Teacher temp = new Teacher(loginForm.getUsername(),loginForm.getPassword());
                        Teacher teacher = teacherService.getByEntity(temp);
                        if(!isEmpty(teacher)){
                                HttpSession session = request.getSession();
                                session.setAttribute(Consts.USERTYPE,2);
                                session.setAttribute(Consts.TEACHER,teacher);
                                model.addAttribute("obj",teacher);
                                return "login/tIndex";
                        }
                        break;
                case 3:
                    Manage temp2 = new Manage(loginForm.getUsername(),loginForm.getPassword());
                    Manage manage =  manageService.getByEntity(temp2);
                    if(!isEmpty(manage)){
                            HttpSession session = request.getSession();
                            session.setAttribute(Consts.USERTYPE,3);
                            session.setAttribute(Consts.MANAGE,manage);
                            model.addAttribute("obj",manage);
                            return "login/mIndex";
                    }
                    break;
            }
            return  null;
    }

}
