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
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "clubs")
public class Club {

    @Id
    String manufacturer;
    @NonNull
    String model;
    @NonNull
    String type;

//    @ManyToMany(mappedBy = "clubs")
//    private Set<Player> players = new LinkedHashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Club club = (Club) o;
        return manufacturer.equals(club.manufacturer) && model.equals(club.model) && type.equals(club.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(manufacturer, model, type);
    }

}
