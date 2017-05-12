package com.theironyard.CalendarSpring.controllers;

import com.theironyard.CalendarSpring.entities.Event;
import com.theironyard.CalendarSpring.entities.User;
import com.theironyard.CalendarSpring.services.EventRepository;
import com.theironyard.CalendarSpring.services.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * Created by Keith on 5/11/17.
 */
@Controller
public class CalendarSpringController {
    @Autowired
    EventRepository events;

    @Autowired
    UserRepository users;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, HttpSession session) {
        String userName = (String) session.getAttribute("userName");
        List<Event> eventEntities = events.findAllByOrderByStartDateTimeDesc();
        List<Event> userEvents;

        if (userName != null) {
            User user = users.findAllByName(userName);
            model.addAttribute("user", user);
            model.addAttribute("now", LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
            userEvents = events.findAllByUser(user);
            model.addAttribute("userEvents",userEvents);
        }
        model.addAttribute("events", eventEntities);
        return "home";
    }

    @RequestMapping(path = "/create-event", method = RequestMethod.POST)
    public String createEvent(HttpSession session, String description, String startDateTime, String endDateTime) {
        String userName = (String) session.getAttribute("userName");
        if (userName != null) {
            Event event = new Event(description, LocalDateTime.parse(startDateTime), LocalDateTime.parse(endDateTime), users.findAllByName(userName));

            List<Event> collidingStartTimes = events.findAllByStartDateTimeBetween(LocalDateTime.parse(startDateTime), LocalDateTime.parse(endDateTime));
            List<Event> collidingEndTimes = events.findAllByEndDateTimeBetween(LocalDateTime.parse(startDateTime), LocalDateTime.parse(endDateTime));
            LocalDateTime currentTime = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
            LocalDateTime startTime = LocalDateTime.parse(startDateTime);

            if(collidingStartTimes.size() == 0 && collidingEndTimes.size() == 0) {
                if (startTime.isAfter(currentTime)) {
                    events.save(event);
                }
            }
        }
        return "redirect:/";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, String name) {
        User user = users.findAllByName(name);
        if (user == null) {
            user = new User(name);
            users.save(user);
        }
        session.setAttribute("userName", name);
        return "redirect:/";
    }

    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}

