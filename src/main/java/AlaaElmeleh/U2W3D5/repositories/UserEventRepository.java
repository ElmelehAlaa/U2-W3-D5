package AlaaElmeleh.U2W3D5.repositories;

import AlaaElmeleh.U2W3D5.entities.UserEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserEventRepository extends JpaRepository<UserEvent, Long> {
    List<UserEvent> findAllByUserId(Long userId);

    List<UserEvent> findAllByEventId(Long eventId);
}
