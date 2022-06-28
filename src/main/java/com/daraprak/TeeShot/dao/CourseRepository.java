package com.daraprak.TeeShot.dao;

import com.daraprak.TeeShot.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    // DB Query to find courses by name
   @Query(value = "select * from courses where name = :name", nativeQuery = true)
   List<Course> findByCourse(String name);

}