package com.sun.Controller;

import com.sun.Base.BaseController;
import com.sun.Common.LogAnno;
import com.sun.Entity.Manage;
import com.sun.Service.ManageService;
import com.sun.Utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/manage")
public class manageController extends BaseController {
        @Autowired
        private ManageService manageService;

        @LogAnno(value = "检索")
        @RequestMapping("/findBySql")
        public  String findBySql(Manage manage,Model model){
                String sql = "select  * from manage where 1=1";
                if(!isEmpty(manage.getUsername())){
                        sql += " and m_username like '%"+manage.getUsername() + "%' ";
                }
                sql += " order by m_id";
                Pager<Manage> pagers = manageService.findBySqlRerturnEntity(sql);
                model.addAttribute("pagers",pagers);
                model.addAttribute("obj",manage);
                return "manage/manage";
        }

        @LogAnno(value = "获取个人信息")
        @RequestMapping("/getInfo")
        public String getInfo(Integer id, Model model){
                Manage manage = manageService.load(id);
                if(!isEmpty(manage)){
                        model.addAttribute("obj",manage);
                }
                return "/manage/info";
        }

        //管理员删除.管理员不能够删除自己的信息,只能被其他管理员删除
        @LogAnno(value = "删除管理员")
        @RequestMapping("/deleteById")
        public String deleteById(Integer id){
                Manage manage = manageService.load(id);
                if(!isEmpty(manage)){
//                        匹配管理员每个信息,然后进行删除
                        manageService.deleteByEntity(manage);
                }
                return "redirect:/manage/findBySql";
        }

}
