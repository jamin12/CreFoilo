package emyo.jamin.jej.crefoilo.utils;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    /* 400 BAD_REQUEST : 잘못된 요청 */

    /* 401 UNAUTHORIZED : 인증되지 않은 사용자 */
    /* 404 NOT_FOUND : Resource 를 찾을 수 없음 */
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 유저 정보를 찾을 수 없습니다"),
    POST_NOT_FOUND(HttpStatus.BAD_REQUEST, "포스트가 없습니다."),
    PROJECT_NOT_FOUND(HttpStatus.BAD_REQUEST, "프로젝트가 없습니다."),
    ABOUT_NOT_FOUND(HttpStatus.BAD_REQUEST, "AboutMe가 없습니다."),
    /* 500 Internal Server Error 서버가 처리 방법을 모르는 상황이 발생했습니다. */
    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버에러"),
    ;

    private final HttpStatus httpStatus;
    private final String detail;
}
