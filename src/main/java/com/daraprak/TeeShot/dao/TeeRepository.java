package com.daraprak.TeeShot.dao;

import com.daraprak.TeeShot.models.Tee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeeRepository extends JpaRepository<Tee, Integer> {
    @Query(value = "select * from courses where name = :name", nativeQuery = true)
    List<Tee> findByCourse(String name);

}