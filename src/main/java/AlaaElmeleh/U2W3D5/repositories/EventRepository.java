package AlaaElmeleh.U2W3D5.repositories;

import AlaaElmeleh.U2W3D5.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    Optional<Event> findById(Long id);
}
