package co.istad.ecommerceapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import javax.management.relation.RelationServiceNotRegisteredException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ApiException {

    @ExceptionHandler(ResponseStatusException.class)
    ResponseEntity<?> handleResponseStatus(ResponseStatusException e) {
        ErrorDetailResponse<?> errorDetailResponse = ErrorDetailResponse.builder()
                .code(e.getStatusCode().toString())
                .description(e.getReason())
                .build();
        return new ResponseEntity<>(ErrorResponse.builder()
                .error(errorDetailResponse)
                .build(), e.getStatusCode());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ErrorResponse<?> handleResponseStatus(MethodArgumentNotValidException e) {
        List<Map<String, String>> errorDetail = new ArrayList<>();
        e.getFieldErrors()
                .forEach(fieldError -> {
                    Map<String, String> errorDetailMap = new HashMap<>();
                    errorDetailMap.put("field", fieldError.getField());
                    errorDetailMap.put("reason", fieldError.getDefaultMessage());
                    errorDetail.add(errorDetailMap);
                });
        ErrorDetailResponse<?> errorDetailResponse =ErrorDetailResponse.builder()
                .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .description(errorDetail)
                .build();
        return ErrorResponse.builder()
                .error(errorDetailResponse)
                .build();
    }
}
