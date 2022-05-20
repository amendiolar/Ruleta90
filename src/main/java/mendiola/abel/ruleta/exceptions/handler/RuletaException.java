package mendiola.abel.ruleta.exceptions.handler;

import mendiola.abel.ruleta.exceptions.BadRequestException;
import mendiola.abel.ruleta.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

public class RuletaException
{
    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<Object> formatoInvalidoException(BadRequestException exception)
    {
        Map<String, Object> respuesta = new HashMap<String, Object>();
        respuesta.put("error", exception.getMessage());
        return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<?> noExisteException(NotFoundException exception)
    {
        Map<String, Object> respuesta = new HashMap<String, Object>();
        respuesta.put("error", exception.getMessage());
        return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
    }
}
