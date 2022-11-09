package emyo.jamin.jej.crefoilo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import emyo.jamin.jej.crefoilo.dto.AboutmeDto;
import emyo.jamin.jej.crefoilo.entity.AboutMe;
import emyo.jamin.jej.crefoilo.repository.AboutmeRepository;
import emyo.jamin.jej.crefoilo.utils.CustomException;
import emyo.jamin.jej.crefoilo.utils.ErrorCode;

@Service
public class AboutmeServiceImpl implements AboutmeService {
    @Autowired
    private AboutmeRepository aboutmeRepository;

    /**
     * 포트폴리오에 맞는 AboutMe 조회
     * 
     * @param portfolioId
     */
    @Override
    public AboutmeDto findAboutme(Long portfolioId) {
        AboutMe findedAboutMe = aboutmeRepository.findByPortfolioId(portfolioId);
        if (findedAboutMe == null) {
            throw new CustomException(ErrorCode.ABOUT_NOT_FOUND);
        }
        return new AboutmeDto(findedAboutMe);
    }
}
