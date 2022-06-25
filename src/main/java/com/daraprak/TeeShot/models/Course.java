package com.daraprak.TeeShot.models;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.NumberFormat;

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
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @NonNull
    String name;
    @NonNull
    int golfers;
    @NonNull
    @NumberFormat(style= NumberFormat.Style.CURRENCY)
    double rate;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.EAGER)
    @JoinTable(name = "courses_tee_times",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "tee_times_id"))
    private Set<TeeTime> teeTimes = new LinkedHashSet<>();

    public Course(String name, int golfers, double rate) {
        this.name = name;
        this.golfers = golfers;
        this.rate = rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id == course.id && golfers == course.golfers && Double.compare(course.rate, rate) == 0 && name.equals(course.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, golfers, rate);
    }
}
