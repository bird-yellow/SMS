package com.sun.Service.Impl;

import com.sun.Base.BaseDao;
import com.sun.Base.BaseServiceImpl;
import com.sun.Dao.StudentDao;
import com.sun.Entity.Student;
import com.sun.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl extends BaseServiceImpl<Student> implements StudentService {
    @Autowired
    private StudentDao studentDao;

    @Override
    public BaseDao<Student> getBaseDao() {
        return studentDao;
    }
}
