package com.theironyard.CalendarSpring.services;

import com.theironyard.CalendarSpring.entities.Event;
import com.theironyard.CalendarSpring.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Keith on 5/11/17.
 */
public interface EventRepository extends CrudRepository<Event, Integer> {
    List<Event> findAllByOrderByStartDateTimeDesc();
    List<Event> findAllByStartDateTimeBetween(LocalDateTime parse, LocalDateTime localDateTime);
    List<Event>findAllByEndDateTimeBetween(LocalDateTime parse, LocalDateTime localDateTime);
    List<Event> findAllByUser(User user);

}

