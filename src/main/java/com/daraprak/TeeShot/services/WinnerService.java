package com.daraprak.TeeShot.services;

import com.daraprak.TeeShot.dao.WinnerRepository;
import com.daraprak.TeeShot.dao.TournamentRepository;
import com.daraprak.TeeShot.models.Winner;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@Transactional(rollbackOn = {DataAccessException.class})
public class WinnerService {

    WinnerRepository winnerRepository;
    TournamentRepository tournamentRepository;

    @Autowired
    public WinnerService(WinnerRepository winnerRepository, TournamentRepository tournamentRepository) {
        this.winnerRepository = winnerRepository;
        this.tournamentRepository = tournamentRepository;
    }

    public List<Winner> findAll() {
        return winnerRepository.findAll();
    }

    public List<Winner> findAllWinners() {
        return winnerRepository.findAllWinners();
    }

    @Transactional(rollbackOn = {NoSuchElementException.class})
    public Winner findByEmail(String email) throws NoSuchElementException {
        return winnerRepository.findById(email).orElseThrow();
    }

    public void saveOrUpdate(Winner winner) {
        log.info(winner.toString());
        winnerRepository.save(winner);
    }

    public void isWinner(Winner winner) {
        winner.setWinner(true);
    }

}
