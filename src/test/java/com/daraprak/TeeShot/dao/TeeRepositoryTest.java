package com.daraprak.TeeShot.dao;

import com.daraprak.TeeShot.models.Tee;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@FieldDefaults(level = AccessLevel.PRIVATE)
class TeeRepositoryTest {

    @Autowired
    TeeRepository teeRepository;
    Tee tee;

    @BeforeEach
    public void setup() {
        tee = Tee.builder()
                .id(1)
                .name("Cave Creek")
                .golfers(4)
                .date(LocalDate.now())
                .build();
    }

    // JUnit test for save tee
    @DisplayName("JUnit test for save tee")
    @Test
    void saveTee() {
        tee = Tee.builder()
                .id(1)
                .name("Cave Creek")
                .golfers(4)
                .date(LocalDate.now())
                .build();

        Tee savedTee = teeRepository.save(tee);

        assertThat(savedTee).isNotNull();
        assertEquals("Cave Creek", savedTee.getName());

    }
}