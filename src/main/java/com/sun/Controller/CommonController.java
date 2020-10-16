package com.sun.Controller;

import com.sun.Entity.MailSendInfo;
import com.sun.Entity.Manage;
import com.sun.Entity.Student;
import com.sun.Entity.Teacher;
import com.sun.Mail.HtmlMessage;
import com.sun.Service.ManageService;
import com.sun.Service.StudentService;
import com.sun.Service.TeacherService;
import com.sun.Utils.Consts;
import com.sun.Utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/common")
public class CommonController {
        @Autowired
        private StudentService studentService;

        @Autowired
        private TeacherService teacherService;
        @Autowired
        private ManageService manageService;
        @Autowired
        private HtmlMessage  htmlMessage;

        @RequestMapping("/getVerificationCode")
        public  String getVerificationCode(Integer id,Model model,HttpServletRequest request){
            Manage manage = manageService.load(id);
            MailSendInfo mailSendInfo = new MailSendInfo();
            mailSendInfo.setToAddress(manage.getEmail());
            String code = UUIDUtils.random().substring(0,6);
            mailSendInfo.setContent(code);
            request.getSession().setAttribute(Consts.Code,code);
            htmlMessage.printMessage(mailSendInfo);
            model.addAttribute("obj",manage);
            return "common/verificationCode";
        }

        @RequestMapping("/updatePassword")
        public String updatePassword(Integer id,String code, Model model, HttpServletRequest request){
            model.addAttribute("id",id);
            int type = (Integer)request.getSession().getAttribute(Consts.USERTYPE);
            System.out.println("用户类型=" + type);
            model.addAttribute("type",type);
            String code1 =(String) request.getSession().getAttribute(Consts.Code);
            System.out.println("验证码1=" + code + "验证码2=" + code1);
            if(code.equals(code1)){
                return "common/updatePassword";
            }
            else{
                return "common/404";
            }
        }

        @RequestMapping("/exUpdatePassword")
        public String updatePassword(Integer id,Integer type,String oldPassword,String newPassword){
                Object obj = null;
                switch(type){
                    case 1:
                        obj = studentService.load(id);
                        Student tmp = (Student)obj;
                        if(tmp.getPassword().equals(oldPassword)){
                                tmp.setPassword(newPassword);
                                studentService.updateById(tmp);
                        }
                        return "redirect:/login/logout.action";
                    case 2:
                        obj = teacherService.load(id);
                        Teacher t = (Teacher)obj;
                        if(t.getPassword().equals(oldPassword)){
                            t.setPassword(newPassword);
                            teacherService.updateById(t);
                        }
                        teacherService.updateById(t);
                        return "redirect:/login/logout.action";
                    case 3:
                        obj = manageService.load(id);
                        Manage m = (Manage)obj;
                        if(m.getPassword().equals(oldPassword)){
                            m.setPassword(newPassword);
                            manageService.updateById(m);
                        }
                        return "redirect:/login/logout";
                }
                return null;
        }
}
