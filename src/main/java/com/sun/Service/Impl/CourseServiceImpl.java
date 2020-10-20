package com.sun.Service.Impl;

import com.sun.Base.BaseDao;
import com.sun.Base.BaseServiceImpl;
import com.sun.Dao.CourseDao;
import com.sun.Entity.Course;
import com.sun.Service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl extends BaseServiceImpl<Course> implements CourseService {
    @Autowired
    private CourseDao courseDao;

    @Override
    public BaseDao<Course> getBaseDao() {
        return courseDao;
    }

    @Override
    public Course verifiCourseExists(Course course) {
        return courseDao.verifiCourseExists(course);
    }

    @Override
    public void deleteByStudentId(Integer id) {
            courseDao.deleteByStudentId(id);
    }
}
