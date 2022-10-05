package emyo.jamin.jej.crefoilo.security;

import java.io.Serializable;

import emyo.jamin.jej.crefoilo.entity.User;
import lombok.Getter;

/**
 * 세션 데이터베이스에 들어갈 정보 직렬화
 */
@Getter
public class SessionUser implements Serializable {
    private String snsType;
    private String snsName;
    private String userEmail;

    public SessionUser(User user) {
        this.userEmail = user.getUserEmail();
    }
}