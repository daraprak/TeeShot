package com.daraprak.BirdieGolf.models;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

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

    @ToString.Exclude
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.EAGER)
    @JoinTable(name = "players_tournaments",
            joinColumns = @JoinColumn(name = "player_email"),
            inverseJoinColumns = @JoinColumn(name = "tournaments_id"))
    private Set<Tournament> tournaments = new LinkedHashSet<>();

    public Player(@NonNull String email, @NonNull String firstName, @NonNull String lastName, @NonNull String password) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }



//    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
//    @JoinTable(name = "players_clubs",
//            joinColumns = @JoinColumn(name = "player_email"),
//            inverseJoinColumns = @JoinColumn(name = "clubs_manufacturer"))
//    private Set<Club> clubs = new LinkedHashSet<>();

    // Helper Method
    public void addTournament(Tournament tournament) {
        tournaments.add(tournament);
        tournament.getPlayers().add(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return email.equals(player.email) && firstName.equals(player.firstName) && lastName.equals(player.lastName) && password.equals(player.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, firstName, lastName, password);
    }

}
