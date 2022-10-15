package emyo.jamin.jej.crefoilo.utils;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { Exception.class })
    protected ResponseEntity<ErrorResponse> handleDataException(Exception e) {
        log.error("handleDataException throw Exception : {}", e.getMessage());
        throw new CustomException(ErrorCode.INTERNAL_ERROR);
    }

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
