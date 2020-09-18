package com.sun.Service.Impl;

import com.sun.Base.BaseDao;
import com.sun.Base.BaseServiceImpl;
import com.sun.Dao.GradeDao;
import com.sun.Entity.Grade;
import com.sun.Service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GradeServiceImpl extends BaseServiceImpl<Grade> implements GradeService {
        @Autowired
        private GradeDao gradeDao;

    @Override
    public BaseDao<Grade> getBaseDao() {
        return gradeDao;
    }
}
