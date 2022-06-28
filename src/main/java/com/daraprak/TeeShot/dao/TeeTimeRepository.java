package com.daraprak.TeeShot.dao;

import com.daraprak.TeeShot.models.TeeTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeeTimeRepository extends JpaRepository<TeeTime, Integer> {

    // DB Query to find tee times by courses
    @Query(value = "select date, time from courses c join teetimes t on c.id = t.id where name = :name", nativeQuery = true)
    List<TeeTime> findTeeTimesByCourse(String name);

}