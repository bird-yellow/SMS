package com.sun.Service;

import com.sun.Base.BaseService;
import com.sun.Entity.Course;

public interface CourseService extends BaseService<Course> {
        Course  verifiCourseExists(Course course);
        public  void deleteByStudentId(Integer id);

}
