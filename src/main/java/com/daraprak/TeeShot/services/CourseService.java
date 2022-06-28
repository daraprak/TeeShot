package com.daraprak.TeeShot.services;

import com.daraprak.TeeShot.dao.CourseRepository;
import com.daraprak.TeeShot.dao.TeeTimeRepository;
import com.daraprak.TeeShot.models.Course;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@Transactional(rollbackOn = {DataAccessException.class})
public class CourseService {

    CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public List<Course> findByCourse(String name) {
        return courseRepository.findByCourse(name);
    }

    public void saveOrUpdate(Course course) {
        courseRepository.save(course);
    }

}