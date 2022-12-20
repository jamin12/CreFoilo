package emyo.jamin.jej.crefoilo.utils;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { Exception.class })
    protected String handleDataException(Exception e, Model model) {
        model.addAttribute("error", ErrorCode.INTERNAL_ERROR.getHttpStatus().value());
        return "error/errorpage";
    }

    // 커스텀 에러만 받는 함수
    @ExceptionHandler(value = { CustomException.class })
    protected String handleCustomException(CustomException e, Model model) {
        log.error("handleCustomException  throw CustomException  : {}", e.getErrorCode());
        // return ErrorResponse.toResponseEntity(e.getErrorCode());
        model.addAttribute("error", e.getErrorCode().getHttpStatus().value());
        return "error/errorpage";
    }

    // @ExceptionHandler({ Exception.class })
    // protected ResponseEntity<ErrorResponse> handleServerException(Exception ex) {
    // return new ErrorResponse(ex);
    // }
}
