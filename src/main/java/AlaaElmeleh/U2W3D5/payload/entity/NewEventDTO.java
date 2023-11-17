package AlaaElmeleh.U2W3D5.payload.entity;

import AlaaElmeleh.U2W3D5.entities.User;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;

public record NewEventDTO(@NotEmpty(message = "il titolo é obbligatorio!")
                          String titolo,
                          @NotEmpty(message = "i posti disponibili sono obbligatori!")
                          int placesAvailable,
                          @NotEmpty(message = "la descrizione é obbligatoria!")
                          String description,
                          @NotEmpty(message = "la data è obbligatoria!")
                          LocalDate date,
                          @NotEmpty(message = "il luogo é obbligatorio!")
                          String place,
                          User organizer


) {
}
