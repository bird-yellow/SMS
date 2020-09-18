package com.sun.Service.Impl;

import com.sun.Base.BaseDao;
import com.sun.Base.BaseServiceImpl;
import com.sun.Dao.DeparmentDao;
import com.sun.Entity.Deparment;
import com.sun.Service.DeparmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeparmentServiceImpl extends BaseServiceImpl<Deparment> implements DeparmentService {
        @Autowired
        private DeparmentDao deparmentDao;

    @Override
    public BaseDao<Deparment> getBaseDao() {
        return deparmentDao;
    }
}
