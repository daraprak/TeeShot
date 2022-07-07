package com.daraprak.TeeShot.models;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "players")
public class Player {

    @Id
    @NonNull
    String email;
    @NonNull
    String firstName;
    @NonNull
    String lastName;
    @NonNull
    @Setter(AccessLevel.NONE)
    String password;
    @NonNull
    boolean winner;
    @NonNull
    double totalWinnings;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.EAGER)
    @JoinTable(name = "players_tee_times",
            joinColumns = @JoinColumn(name = "player_email"),
            inverseJoinColumns = @JoinColumn(name = "tee_times_id"))
    private Set<Tee> tees = new LinkedHashSet<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.EAGER)
    @JoinTable(name = "players_tournaments",
            joinColumns = @JoinColumn(name = "player_email"),
            inverseJoinColumns = @JoinColumn(name = "tournaments_id"))
    private Set<Tournament> tournaments = new LinkedHashSet<>();


    public Player(@NonNull String email, @NonNull String firstName, @NonNull String lastName, @NonNull String password, @NonNull boolean winner) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = new BCryptPasswordEncoder().encode(password);
        this.winner = winner;
    }

    public Player(@NonNull String email, @NonNull String firstName, @NonNull String lastName, @NonNull String password, @NonNull boolean winner, @NonNull double totalWinnings) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = new BCryptPasswordEncoder().encode(password);
        this.winner = winner;
        this.totalWinnings = totalWinnings;
    }

    public void setPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player1 = (Player) o;
        return winner == player1.winner && Double.compare(player1.totalWinnings, totalWinnings) == 0 && email.equals(player1.email) && firstName.equals(player1.firstName) && lastName.equals(player1.lastName) && password.equals(player1.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, firstName, lastName, password, winner, totalWinnings);
    }
}
