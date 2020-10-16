package com.sun.Service.Impl;

import com.sun.Base.BaseDao;
import com.sun.Base.BaseServiceImpl;
import com.sun.Dao.LogDao;
import com.sun.Entity.Log;
import com.sun.Service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl extends BaseServiceImpl<Log> implements LogService {
    @Autowired
    private LogDao logDao;

    @Override
    public BaseDao<Log> getBaseDao() {
        return logDao;
    }

    @Override
    public void clear(Integer value) {
        logDao.clear(value);
    }
}
