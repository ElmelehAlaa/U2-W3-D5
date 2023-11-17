package AlaaElmeleh.U2W3D5.payload.entity;

import AlaaElmeleh.U2W3D5.entities.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record NewEventDTO(@NotEmpty(message = "il titolo é obbligatorio!")
                          String titolo,
                          @NotNull(message = "i posti disponibili sono obbligatori!")
                          long placesAvailable,
                          @NotEmpty(message = "la descrizione é obbligatoria!")
                          String description,
                          @NotNull(message = "la data è obbligatoria!")
                          Date date,
                          @NotEmpty(message = "il luogo é obbligatorio!")
                          String place,
                          User organizer


) {
}
