package com.sun.Dao;

import com.sun.Base.BaseDao;
import com.sun.Entity.Course;

public interface CourseDao  extends BaseDao<Course> {
        Course  verifiCourseExists(Course course);
        public  void deleteByStudentId(Integer id);

}
