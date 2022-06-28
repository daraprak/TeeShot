package com.daraprak.TeeShot.models;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
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
@Table(name = "teetimes")
public class TeeTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @NonNull
    LocalDate date;
    LocalTime time;

    @ManyToMany(mappedBy = "teeTimes", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.EAGER)
    private Set<Course> courses = new LinkedHashSet<>();

    public TeeTime(LocalDate date, LocalTime time) {
        this.date = date;
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeeTime teeTime = (TeeTime) o;
        return id == teeTime.id && date.equals(teeTime.date) && time.equals(teeTime.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, time);
    }

}
