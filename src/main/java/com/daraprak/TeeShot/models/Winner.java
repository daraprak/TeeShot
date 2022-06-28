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
@Table(name = "winners")
public class Winner {

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
    boolean winner = false;
    @NonNull
    double totalWinnings;

    public Winner(@NonNull String email, @NonNull String firstName, @NonNull String lastName, @NonNull String password, @NonNull boolean winner) {
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
        Winner winner1 = (Winner) o;
        return winner == winner1.winner && Double.compare(winner1.totalWinnings, totalWinnings) == 0 && email.equals(winner1.email) && firstName.equals(winner1.firstName) && lastName.equals(winner1.lastName) && password.equals(winner1.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, firstName, lastName, password, winner, totalWinnings);
    }

}
