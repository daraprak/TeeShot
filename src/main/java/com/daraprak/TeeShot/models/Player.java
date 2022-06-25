package com.daraprak.TeeShot.models;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
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
    String password;
    @NonNull
    boolean winner;
    @NonNull
    double totalWinnings;

    public Player(@NonNull String email, @NonNull String firstName, @NonNull String lastName, @NonNull String password, @NonNull boolean winner) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.winner = winner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return winner == player.winner && Double.compare(player.totalWinnings, totalWinnings) == 0 && email.equals(player.email) && firstName.equals(player.firstName) && lastName.equals(player.lastName) && password.equals(player.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, firstName, lastName, password, winner, totalWinnings);
    }

}
