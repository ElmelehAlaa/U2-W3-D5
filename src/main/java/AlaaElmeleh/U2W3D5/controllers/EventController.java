package AlaaElmeleh.U2W3D5.controllers;

import AlaaElmeleh.U2W3D5.entities.Event;
import AlaaElmeleh.U2W3D5.entities.User;
import AlaaElmeleh.U2W3D5.exceptions.BadRequestException;
import AlaaElmeleh.U2W3D5.payload.entity.NewEventDTO;
import AlaaElmeleh.U2W3D5.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events")
public class EventController {
    @Autowired
    private EventService eventService;

    @GetMapping("")
    public Page<Event> getEvents(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int size,
                                 @RequestParam(defaultValue = "id") String orderBy) {
        return eventService.getEvents(page, size, orderBy);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ORGANIZER')")
    public Event saveEvent(@RequestBody @Validated NewEventDTO body, BindingResult validation, Authentication authentication) {

        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        } else {
            User organizer = (User) authentication.getPrincipal();
            return eventService.save(body, organizer);
        }
    }

    @GetMapping(value = "/{id}")
    public Event findById(@PathVariable long id) {
        return eventService.findById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ORGANIZER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable long id) {
        eventService.findByIdAndDelete(id);
    }

    @PostMapping("/{id}/prenota")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void prenotaPosto(
            @PathVariable long id,
            @RequestParam long numeroPosti,
            Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        eventService.prenotaPosto(id, user, numeroPosti);
    }
}
