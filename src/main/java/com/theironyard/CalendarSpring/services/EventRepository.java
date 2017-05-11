package com.theironyard.CalendarSpring.services;

import com.theironyard.CalendarSpring.entities.Event;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Keith on 5/11/17.
 */
public interface EventRepository extends CrudRepository<Event, Integer> {
    List<Event> findAllByOrderByStartDateTimeDesc();
}

