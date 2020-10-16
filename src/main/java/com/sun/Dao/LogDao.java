package com.sun.Dao;

import com.sun.Base.BaseDao;
import com.sun.Entity.Log;

public interface LogDao  extends BaseDao<Log> {

    //清空日志
    public void clear(Integer value);


}
