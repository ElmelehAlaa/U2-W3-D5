package AlaaElmeleh.U2W3D5.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String description;
    private String place;
    private int placesAvailable;
    private LocalDate date;

   @ManyToOne
    @JoinColumn(name = "organizer_id")
    private User organizer;

   @OneToMany(mappedBy = "event")
    private Set<UserEvent> userEvents;

}
