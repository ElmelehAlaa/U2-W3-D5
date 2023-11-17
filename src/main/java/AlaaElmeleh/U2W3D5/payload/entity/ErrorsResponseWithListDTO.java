package AlaaElmeleh.U2W3D5.payload.entity;

import java.util.Date;
import java.util.List;

public record ErrorsResponseWithListDTO(
        Date timestamp,
        List<String> errorsList) {
}
