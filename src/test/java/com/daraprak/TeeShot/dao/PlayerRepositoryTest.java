package com.daraprak.TeeShot.dao;

import com.daraprak.TeeShot.models.Player;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@FieldDefaults(level = AccessLevel.PRIVATE)
class PlayerRepositoryTest {

    @Autowired
    PlayerRepository playerRepository;
    Player player;

    @BeforeEach
    public void setup() {
        player = Player.builder()
                .firstName("Michael")
                .lastName("Scott")
                .email("michael@gmail.com")
                .password("password")
                .build();
    }

    // JUnit test for save player
    @DisplayName("JUnit test for save player")
    @Test
    void savePlayer() {
        Player player = Player.builder()
                .firstName("Michael")
                .lastName("Scott")
                .email("michael@gmail.com")
                .password("password")
                .winner(true)
                .build();

        Player savedPlayer = playerRepository.save(player);

        assertThat(savedPlayer).isNotNull();
        assertEquals("michael@gmail.com", savedPlayer.getEmail());
    }

    // JUnit test for find all players
    @DisplayName("JUnit test for find all players")
    @Test
    void findAllPlayers() {
        Player player1 = Player.builder()
                .firstName("Optimus")
                .lastName("Prime")
                .email("OP@gmail.com")
                .password("password")
                .winner(true)
                .build();

        playerRepository.save(player);
        playerRepository.save(player1);

        List<Player> playerList = playerRepository.findAllPlayers();

        assertThat(playerList).isNotNull().hasSize(1);
    }

    // JUnit test for update player
    @DisplayName("JUnit test for update employee")
    @Test
    void updatePlayer() {
        playerRepository.save(player);

        Player savedPlayer = playerRepository.findById(player.getEmail()).get();
        savedPlayer.setEmail("scott@gmail.com");
        savedPlayer.setFirstName("Scott");
        Player updatedPlayer = playerRepository.save(savedPlayer);

        assertThat(updatedPlayer.getEmail()).isEqualTo("scott@gmail.com");
        assertThat(updatedPlayer.getFirstName()).isEqualTo("Scott");
    }

    // JUnit test for delete player
    @DisplayName("JUnit test for delete player")
    @Test
    void deletePlayer() {
        playerRepository.save(player);

        playerRepository.deleteById(player.getEmail());
        Optional<Player> playerOptional = playerRepository.findById(player.getEmail());

        assertThat(playerOptional).isEmpty();
    }
}