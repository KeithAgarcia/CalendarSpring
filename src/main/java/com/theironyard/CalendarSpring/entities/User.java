package com.theironyard.CalendarSpring.entities;

import javax.persistence.*;

/**
 * Created by Keith on 5/11/17.
 */@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false, unique = true)
    String name;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }
}
