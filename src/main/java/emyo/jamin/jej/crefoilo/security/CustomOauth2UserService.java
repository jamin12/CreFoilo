package emyo.jamin.jej.crefoilo.security;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import emyo.jamin.jej.crefoilo.dto.SessionDto;
import emyo.jamin.jej.crefoilo.entity.SnsInfo;
import emyo.jamin.jej.crefoilo.entity.Users;
import emyo.jamin.jej.crefoilo.repository.SnsInfoRepository;
import emyo.jamin.jej.crefoilo.repository.UserRepository;

@Service
public class CustomOauth2UserService
    implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private SnsInfoRepository snsInfoRepository;

  @Autowired
  private HttpSession httpSession;

  @Override
  public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest)
      throws OAuth2AuthenticationException {
    OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService = new DefaultOAuth2UserService();
    OAuth2User oAuth2User = oAuth2UserService.loadUser(oAuth2UserRequest);

    // 현재 진행중인 서비스를 구분하기 위해 문자열로 받음.
    // oAuth2UserRequest.getClientRegistration().getRegistrationId()에 값이 들어있다.
    // {registrationId='naver'} 이런식으로
    String registrationId = oAuth2UserRequest
        .getClientRegistration()
        .getRegistrationId();

    // OAuth2 로그인 시 키 값이 된다. 구글은 키 값이 "sub"이고, 네이버는 "response"이고, 카카오는 "id"이다. 각각
    // 다르므로 이렇게 따로 변수로 받아서 넣어줘야함.
    String userNameAttributeName = oAuth2UserRequest
        .getClientRegistration()
        .getProviderDetails()
        .getUserInfoEndpoint()
        .getUserNameAttributeName();

    // OAuth2 로그인을 통해 가져온 OAuth2User의 attribute를 담아주는 of 메소드.
    OauthAttributes attributes = OauthAttributes.of(
        registrationId,
        userNameAttributeName,
        oAuth2User.getAttributes());

    SessionDto toSession = saveOrUpdate(attributes);
    httpSession.setAttribute("user", new SessionUser(toSession));
    return new DefaultOAuth2User(
        Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")),
        attributes.getAttributes(),
        attributes.getNameAttributeKey());
  }

  // 혹시 이미 저장된 정보라면, update 처리
  @Transactional
  private SessionDto saveOrUpdate(OauthAttributes attributes) {
    List<Users> user = userRepository.findByEmail(attributes.getUserEmail());
    List<SnsInfo> snsinfo = snsInfoRepository.findByEmail(attributes.getUserEmail());
    SessionDto sessionDto = new SessionDto();

    if (user.isEmpty()) {
      Users createdUser = userRepository.save(attributes.toEntity());
      SnsInfo createdSnsInfo = snsInfoRepository.save(attributes.toEntitySns());
      sessionDto.setUserId(createdUser.getId());
      sessionDto.setSnsType(createdSnsInfo.getSnsType());
      sessionDto.setSnsName(createdSnsInfo.getSnsName());
      sessionDto.setUserEmail(createdUser.getUserEmail());
      return sessionDto;
    }
    Users updatedUser = userRepository.save(user.get(0).update(attributes.getUserEmail()));
    SnsInfo updatedSnsInfo = snsInfoRepository
        .save(snsinfo.get(0).update(attributes.getUserEmail(), attributes.getSnsName(), attributes.getSnsImg()));
    sessionDto.setUserId(updatedUser.getId());
    sessionDto.setSnsType(updatedSnsInfo.getSnsType());
    sessionDto.setSnsName(updatedSnsInfo.getSnsName());
    sessionDto.setUserEmail(updatedUser.getUserEmail());
    return sessionDto;
  }
}
