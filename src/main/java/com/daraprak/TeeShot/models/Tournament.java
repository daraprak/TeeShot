package com.daraprak.TeeShot.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
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
    String date;
    @NonNull
    String name;
    @NonNull
    String course;
    @NonNull
    double purse;

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
