package com.sun.Dao;

import com.sun.Base.BaseDao;
import com.sun.Entity.Student;

public interface StudentDao extends BaseDao<Student> {
        public void deleteByClazzId(Integer id);
}
