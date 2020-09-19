package com.sun.Service.Impl;

import com.sun.Base.BaseDao;
import com.sun.Base.BaseServiceImpl;
import com.sun.Dao.TeacherDao;
import com.sun.Entity.Teacher;
import com.sun.Service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl extends BaseServiceImpl<Teacher> implements TeacherService {
        @Autowired
        private TeacherDao teacherDao;

    @Override
    public BaseDao<Teacher> getBaseDao() {
        return teacherDao;
    }
}
