package AlaaElmeleh.U2W3D5.exceptions;


import AlaaElmeleh.U2W3D5.payload.entity.ErrorsResponseDTO;
import AlaaElmeleh.U2W3D5.payload.entity.ErrorsResponseWithListDTO;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsResponseWithListDTO handleBadRequest(BadRequestException e ){
        if(e.getErrorList()!= null){
            List<String> errorsList = e.getErrorList().stream().map(objectError -> objectError.getDefaultMessage()).toList();
            return new ErrorsResponseWithListDTO(e.getMessage(),new Date(),new ArrayList<>());
        }else {
            return new ErrorsResponseWithListDTO(e.getMessage(), new Date(), new ArrayList<>());
        }
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorsResponseDTO handleUnauthorized(UnauthorizedException e){
        return new ErrorsResponseDTO(e.getMessage(),new Date());
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorsResponseDTO handleAccessDenied(AccessDeniedException e){
        return new ErrorsResponseDTO(e.getMessage(), new Date());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorsResponseDTO handleNotFound(NotFoundException e){
        return new ErrorsResponseDTO(e.getMessage(), new Date());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorsResponseDTO handleGeneric(Exception e){
        e.printStackTrace();
        return new ErrorsResponseDTO("Problema lato server", new Date());
    }


}
