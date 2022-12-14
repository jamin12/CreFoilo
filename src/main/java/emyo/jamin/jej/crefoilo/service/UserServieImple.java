package emyo.jamin.jej.crefoilo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import emyo.jamin.jej.crefoilo.repository.SnsInfoRepository;

@Service
public class UserServieImple implements UserService {

    @Autowired
    private SnsInfoRepository snsInfoRepository;

    /**
     * 유저 아이디로 이름 찾기
     * 
     * @param userId
     */
    @Override
    public String findUser(String userId) {
        return snsInfoRepository.findByUserId(userId).getSnsName();

    }
}
