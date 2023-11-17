package AlaaElmeleh.U2W3D5.services;

import AlaaElmeleh.U2W3D5.entities.Event;
import AlaaElmeleh.U2W3D5.entities.User;
import AlaaElmeleh.U2W3D5.entities.UserEvent;
import AlaaElmeleh.U2W3D5.exceptions.NotFoundException;
import AlaaElmeleh.U2W3D5.exceptions.PostiUnavailableException;
import AlaaElmeleh.U2W3D5.payload.entity.NewEventDTO;
import AlaaElmeleh.U2W3D5.repositories.EventRepository;
import AlaaElmeleh.U2W3D5.repositories.UserEventRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private UserEventRepository userEventRepository;

    @Autowired
    private Cloudinary cloudinary;


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

    public void prenotaPosto(long eventId, User user, long numberOfPosti) throws NotFoundException, PostiUnavailableException {
        Event event = findById(eventId);
        if (event.getPlacesAvailable() >= numberOfPosti) {
            event.setPlacesAvailable(event.getPlacesAvailable() - numberOfPosti);
            UserEvent userEvent = new UserEvent();
            userEvent.setUser(user);
            userEvent.setEvent(event);
            userEvent.setNumberOfPosti(numberOfPosti);
            userEventRepository.save(userEvent);
            eventRepository.save(event);

        } else {
            throw new PostiUnavailableException("Posti non disponibili per l'evento con id: " + eventId);
        }
    }

    public Event uploadPicture(MultipartFile file, @PathVariable long id) throws IOException {
        Event foundEvent = this.findById(id);
        String cloudinaryURL = (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
        foundEvent.setImageUrl(cloudinaryURL);
        return eventRepository.save(foundEvent);
    }


}
