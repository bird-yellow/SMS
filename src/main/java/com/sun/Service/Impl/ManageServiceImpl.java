package com.sun.Service.Impl;

import com.sun.Base.BaseDao;
import com.sun.Base.BaseServiceImpl;
import com.sun.Dao.ManageDao;
import com.sun.Entity.Manage;
import com.sun.Service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManageServiceImpl extends BaseServiceImpl<Manage> implements ManageService {
    @Autowired
    private ManageDao manageDao;

    @Override
    public BaseDao<Manage> getBaseDao() {
        return manageDao;
    }
}
