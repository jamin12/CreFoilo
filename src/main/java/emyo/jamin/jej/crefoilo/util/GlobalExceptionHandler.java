package emyo.jamin.jej.crefoilo.util;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // @ExceptionHandler(value = { ConstraintViolationException.class,
    // DataIntegrityViolationException.class})
    // protected ResponseEntity<ErrorResponse> handleDataException() {
    // log.error("handleDataException throw Exception : {}", DUPLICATE_RESOURCE);
    // return ErrorResponse.toResponseEntity(DUPLICATE_RESOURCE);
    // }

    // 커스텀 에러만 받는 함수
    @ExceptionHandler(value = { CustomException.class })
    protected ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        log.error("handleCustomException  throw CustomException  : {}", e.getErrorCode());
        return ErrorResponse.toResponseEntity(e.getErrorCode());
    }

    // @ExceptionHandler({ Exception.class })
    // protected ResponseEntity<ErrorResponse> handleServerException(Exception ex) {
    // return new ErrorResponse(ex);
    // }
}
