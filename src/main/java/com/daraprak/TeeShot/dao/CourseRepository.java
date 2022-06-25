package com.daraprak.TeeShot.dao;

import com.daraprak.TeeShot.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    @Query(value = "select * from course where name = :name", nativeQuery = true)
    List<Course> findByCourse(String name);
}
