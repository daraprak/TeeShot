package com.daraprak.TeeShot.services;

import com.daraprak.TeeShot.dao.CourseRepository;
import com.daraprak.TeeShot.dao.TeeTimeRepository;
import com.daraprak.TeeShot.models.Course;
import com.daraprak.TeeShot.models.TeeTime;
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
public class TeeTimeService {

    TeeTimeRepository teeTimeRepository;
    CourseRepository courseRepository;

    @Autowired
    public TeeTimeService(TeeTimeRepository teeTimeRepository, CourseRepository courseRepository) {
        this.teeTimeRepository = teeTimeRepository;
        this.courseRepository = courseRepository;
    }

    public List<TeeTime> findAll() {
        return teeTimeRepository.findAll();
    }

    public void saveOrUpdate(TeeTime teeTime) {
        teeTimeRepository.save(teeTime);
    }

    public List<TeeTime> findTeeTimesByCourse(String name) {
        return teeTimeRepository.findTeeTimesByCourse(name);
    }

}
