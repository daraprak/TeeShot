package com.daraprak.TeeShot.models;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.time.LocalDate;
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
@Table(name = "tees")
public class Tee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @NonNull
    String name;
    @NonNull
    int golfers;
    @NonNull
    LocalDate date;

    @ManyToMany(mappedBy = "tees", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.EAGER)
    private Set<Player> players = new LinkedHashSet<>();

    public Tee(String name, int golfers, LocalDate date) {
        this.name = name;
        this.golfers = golfers;
        this.date = date;
    }

    public Tee(int id, String name, int golfers, LocalDate date) {
        this.id = id;
        this.name = name;
        this.golfers = golfers;
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tee tee = (Tee) o;
        return golfers == tee.golfers && name.equals(tee.name) && date.equals(tee.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, golfers, date);
    }

}
