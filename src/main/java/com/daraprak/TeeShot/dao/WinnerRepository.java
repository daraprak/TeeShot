package com.daraprak.TeeShot.dao;

import com.daraprak.TeeShot.models.Winner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WinnerRepository extends JpaRepository<Winner, String> {
    @Query(value = "select * from winners where winner = 1", nativeQuery = true)
    List<Winner> findAllWinners();
}
