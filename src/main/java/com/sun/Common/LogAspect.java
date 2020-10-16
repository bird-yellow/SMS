package com.sun.Common;

import com.sun.Entity.Log;
import com.sun.Entity.Manage;
import com.sun.Entity.Student;
import com.sun.Entity.Teacher;
import com.sun.Service.LogService;
import com.sun.Utils.Consts;
import com.sun.Utils.HttpContext;
import com.sun.Utils.LogUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

//配置日志切面
@Aspect
@Component
public class LogAspect {
    @Autowired
    private LogService logService;
    private  Log log = new Log();

//        配置切点
        @Pointcut("@annotation(com.sun.Common.LogAnno)")
        public void annotationCut(){}

        @Before("annotationCut()")
        public void before(){
//            System.out.println("开始");
            log.setUsername((String)HttpContext.getSession().getAttribute(Consts.USERNAME));
        }

        @After("annotationCut()")
        public void after(JoinPoint jp) throws Exception {
//            System.out.println("结束");
            Signature signature = jp.getSignature();
            Method method = ((MethodSignature)signature).getMethod();

            Method realMethod = jp.getTarget().getClass().getDeclaredMethod(signature.getName(),method.getParameterTypes());
            LogAnno logAnno = realMethod.getAnnotation(LogAnno.class);


            log.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

            log.setOperator(logAnno.value());
            log.setResult("正常");
            log.setIp(LogUtils.getIpAddress());
            logService.insert(log);
//            System.out.println("数据插入成功");
        }

}
