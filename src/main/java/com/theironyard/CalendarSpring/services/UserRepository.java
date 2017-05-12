package com.theironyard.CalendarSpring.services;

import com.theironyard.CalendarSpring.entities.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Keith on 5/11/17.
 */
public interface UserRepository extends CrudRepository<User, Integer> {
    User findAllByName(String name);
}
