package emyo.jamin.jej.crefoilo.security;

import java.io.Serializable;

import emyo.jamin.jej.crefoilo.dto.SessionDto;
import lombok.Getter;

/**
 * 세션 데이터베이스에 들어갈 정보 직렬화
 */
@Getter
public class SessionUser implements Serializable {
    private String snsType;
    private String snsName;
    private String userEmail;
    private String userId;

    public SessionUser(SessionDto sessionDto) {
        this.userEmail = sessionDto.getUserEmail();
        this.snsType = sessionDto.getSnsType();
        this.snsName = sessionDto.getSnsName();
        this.userId = sessionDto.getUserId();
    }
}