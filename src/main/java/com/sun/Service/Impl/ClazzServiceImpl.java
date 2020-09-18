package com.sun.Service.Impl;

import com.sun.Base.BaseDao;
import com.sun.Base.BaseServiceImpl;
import com.sun.Dao.ClazzDao;
import com.sun.Entity.Clazz;
import com.sun.Service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClazzServiceImpl extends BaseServiceImpl<Clazz> implements ClazzService {

    @Autowired
    private ClazzDao clazzDao;

    @Override
    public BaseDao<Clazz> getBaseDao() {
        return clazzDao;
    }
}
