package emyo.jamin.jej.crefoilo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import emyo.jamin.jej.crefoilo.dto.AboutmeDto;
import emyo.jamin.jej.crefoilo.entity.AboutMe;
import emyo.jamin.jej.crefoilo.repository.AboutmeRepository;
import emyo.jamin.jej.crefoilo.utils.CustomException;
import emyo.jamin.jej.crefoilo.utils.ErrorCode;
import emyo.jamin.jej.crefoilo.utils.Validation;

/**
 * @author 강민진
 */
@Service
public class AboutmeServiceImpl implements AboutmeService {
    @Autowired
    private AboutmeRepository aboutmeRepository;

    @Autowired
    private Validation validation;

    /**
     * 포트폴리오에 맞는 AboutMe 조회
     * 
     * @param portfolioId
     */
    @Override
    public AboutmeDto findAboutme(Long portfolioId) {
        AboutMe findedAboutMe = aboutmeRepository.findByPortfolioId(portfolioId);
        if (findedAboutMe == null) {
            return new AboutmeDto();
        }
        return new AboutmeDto(findedAboutMe);
    }

    /**
     * setting page 작업입니다.
     * 
     */
    /**
     * Aboutme 페이지 조회
     * 
     * @param portfolioId
     * @param userId
     * @return AboutmeDto
     */
    @Override
    public AboutmeDto findAboutme(Long portfolioId, String userId) {
        validation.checkUserHasPortfolio(portfolioId, userId);
        AboutMe findedAboutMe = aboutmeRepository.findByPortfolioId(portfolioId);
        if (findedAboutMe == null) {
            return null;
        }
        return new AboutmeDto(findedAboutMe);
    }

    /**
     * Aboutme 페이지 생성
     * 
     * @param portfolioId
     * @param userId
     */
    @Override
    @Transactional
    public String createAboutMe(Long portfolioId, String userId, AboutmeDto aboutMeDto) {
        validation.checkUserHasPortfolio(portfolioId, userId);
        aboutmeRepository.save(AboutMe.toCreateEntity(portfolioId, aboutMeDto));
        // TODO: return 수정하기
        return null;
    }
}