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
@RequiredArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "tournaments")
@Entity
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @NonNull
    LocalDate date;
    @NonNull
    String name;
    @NonNull
    String course;
    @NonNull
    double purse;

    @ManyToMany(mappedBy = "tournaments", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH} , fetch = FetchType.EAGER)
    private Set<Player> players = new LinkedHashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tournament that = (Tournament) o;
        return id == that.id && Double.compare(that.purse, purse) == 0 && date.equals(that.date) && course.equals(that.course);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, course, purse);
    }

}
