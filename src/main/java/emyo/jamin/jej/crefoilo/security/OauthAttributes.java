package emyo.jamin.jej.crefoilo.security;

import java.util.Map;

import emyo.jamin.jej.crefoilo.entity.Role;
import emyo.jamin.jej.crefoilo.entity.SnsInfo;
import emyo.jamin.jej.crefoilo.entity.Users;
import lombok.Builder;
import lombok.Getter;

@Getter
public class OauthAttributes {
    private Map<String, Object> attributes;
    private String userId;
    private String nameAttributeKey;
    private String snsType;
    private String snsName;
    private String userEmail;
    private String snsImg;

    @Builder
    public OauthAttributes(Map<String, Object> attributes,
            String nameAttributeKey, String snsName,
            String userEmail, String snsImg, String snsType, String userId) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.snsName = snsName;
        this.userEmail = userEmail;
        this.snsImg = snsImg;
        this.snsType = snsType;
        this.userId = userId;
    }

    public static OauthAttributes of(String registrationId,
            String userNameAttributeName,
            Map<String, Object> attributes) {
        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OauthAttributes ofGoogle(String userNameAttributeName,
            Map<String, Object> attributes) {
        return OauthAttributes.builder()
                .userId((String) attributes.get("sub"))
                .snsName((String) attributes.get("name"))
                .userEmail((String) attributes.get("email"))
                .snsImg((String) attributes.get("picture"))
                .snsType("google")
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    /**
     * User 객체 생성
     * 
     * @return {User}
     */
    public Users toEntity() {
        return Users.builder()
                .id(userId)
                .userEmail(userEmail)
                .role(Role.GUEST)
                .build();
    }

    /**
     * snsInfo 객체 생성
     * 
     * @return {SnsInfo}
     */
    public SnsInfo toEntitySns() {
        return SnsInfo.builder()
                .id(userId)
                .snsName(snsName)
                .snsType(snsType)
                .snsImg(snsImg)
                .snsEmail(userEmail)
                .build();
    }
}
