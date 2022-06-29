package com.daraprak.TeeShot.dao;

import com.daraprak.TeeShot.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, String> {
    @Query(value = "select * from players where winner = 1", nativeQuery = true)
    List<Player> findAllPlayers();

}
