package com.daraprak.TeeShot.services;

import com.daraprak.TeeShot.dao.TeeRepository;
import com.daraprak.TeeShot.models.Tee;
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
public class TeeService {

    TeeRepository teeRepository;

    @Autowired
    public TeeService(TeeRepository teeRepository) {
        this.teeRepository = teeRepository;
    }

    public List<Tee> findAll() {
        return teeRepository.findAll();
    }

    public List<Tee> findByCourseName(String name) {
        return teeRepository.findByCourse(name);
    }

    public void saveOrUpdate(Tee tee) {
        teeRepository.save(tee);
    }

    @Transactional(rollbackOn = {NoSuchElementException.class})
    public Object findById(int id) throws NoSuchElementException{
        return teeRepository.findById(id).orElseThrow();
    }

    public Tee getTeeById(int id) {
        return teeRepository.findById(id).orElseThrow();
    }

    public void deleteTee(int id) {
        teeRepository.deleteById(id);
    }
}
