package AlaaElmeleh.U2W3D5.services;

import AlaaElmeleh.U2W3D5.entities.Event;
import AlaaElmeleh.U2W3D5.entities.User;
import AlaaElmeleh.U2W3D5.exceptions.NotFoundException;
import AlaaElmeleh.U2W3D5.payload.entity.NewEventDTO;
import AlaaElmeleh.U2W3D5.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    public Event save(NewEventDTO body, User organizer) {
        Event newEvent = new Event();
        newEvent.setPlacesAvailable(body.placesAvailable());
        newEvent.setDate(body.date());
        newEvent.setTitle(body.titolo());
        newEvent.setPlace(body.place());
        newEvent.setDescription(body.description());
        newEvent.setOrganizer(organizer);
        return eventRepository.save(newEvent);
    }

    public Page<Event> getEvents(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return eventRepository.findAll(pageable);
    }

    public Event findById(long id) throws NotFoundException {
        return eventRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Event findByIdAndUpdate(long id, Event body) throws NotFoundException {
        Event found = this.findById(id);
        found.setDate(body.getDate());
        found.setDescription(body.getDescription());
        found.setPlace(body.getPlace());
        found.setTitle(body.getTitle());
        found.setPlacesAvailable(body.getPlacesAvailable());
        return eventRepository.save(found);
    }

    public void findByIdAndDelete(long id) throws NotFoundException {
        Event found = this.findById(id);
        eventRepository.delete(found);
    }


}
