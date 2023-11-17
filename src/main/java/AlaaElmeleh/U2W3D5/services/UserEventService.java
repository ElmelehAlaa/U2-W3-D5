package AlaaElmeleh.U2W3D5.services;

import AlaaElmeleh.U2W3D5.entities.UserEvent;
import AlaaElmeleh.U2W3D5.repositories.UserEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserEventService {
    @Autowired
    private UserEventRepository userEventRepository;

    public List<UserEvent> findAllByUserId(long userId) {
        return userEventRepository.findAllByUserId(userId);
    }

    public List<UserEvent> findAllByEventId(long eventId) {
        return userEventRepository.findAllByEventId(eventId);
    }
}
