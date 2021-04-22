package pl.marekpedrys.lessonsbackendspring.errors;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Set<String>> handle(MethodArgumentNotValidException e) {
        return e.getFieldErrors()
                .stream()
                .collect(Collectors.groupingBy(
                        FieldError::getField,
                        Collectors.mapping(FieldError::getDefaultMessage, Collectors.toSet())));
    }

//  previous version:
//        public List<String> handle (MethodArgumentNotValidException e){
//            return e.getBindingResult()
//                    .getFieldErrors()
//                    .stream()
//                    .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
//                    .collect(Collectors.toList());
//    }
}
