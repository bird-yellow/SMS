package com.sun.Service;

import com.sun.Base.BaseService;
import com.sun.Entity.Student;

public interface StudentService extends BaseService<Student> {
    public void deleteByClazzId(Integer id);

}
