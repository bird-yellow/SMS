package com.sun.Service;

import com.sun.Base.BaseService;
import com.sun.Entity.Log;

public interface LogService extends BaseService<Log> {

        public void clear(Integer value);
}
