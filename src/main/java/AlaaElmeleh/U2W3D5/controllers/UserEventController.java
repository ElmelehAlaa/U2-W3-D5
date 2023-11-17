package AlaaElmeleh.U2W3D5.controllers;

import AlaaElmeleh.U2W3D5.entities.UserEvent;
import AlaaElmeleh.U2W3D5.services.UserEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user-events")
public class UserEventController {
    @Autowired
    private UserEventService userEventService;

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasAuthority('ORGANIZER')")
    public List<UserEvent> getUserEventsByUserId(@PathVariable long userId) {
        return userEventService.findAllByUserId(userId);
    }

    @GetMapping("/event/{eventId}")
    @PreAuthorize("hasAuthority('ORGANIZER')")
    public List<UserEvent> getUserEventsByEventId(@PathVariable long eventId) {
        return userEventService.findAllByEventId(eventId);
    }
}
