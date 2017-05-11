package com.theironyard.CalendarSpring.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by Keith on 5/11/17.
 */

@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String description;

    @Column(nullable = false)
    LocalDateTime startDateTime;

    @Column(nullable = false)
    LocalDateTime endDateTime;

    @ManyToOne
    User user;

    public Event() {
    }

    public Event(String description, LocalDateTime startDateTime, LocalDateTime endDateTime, User user) {
        this.description = description;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.user = user;
    }
}