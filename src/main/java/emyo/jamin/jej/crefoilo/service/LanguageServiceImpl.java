package emyo.jamin.jej.crefoilo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import emyo.jamin.jej.crefoilo.dto.FindLanguageDto;
import emyo.jamin.jej.crefoilo.entity.Language;
import emyo.jamin.jej.crefoilo.repository.LanguageReposirtory;

/**
 * @author 강경민
 */
@Service
public class LanguageServiceImpl implements LanguageService {
    @Autowired
    private LanguageReposirtory languageReposirtory;

    /**
     * Language 페이지 language와 language Skill 정보 조회
     * 
     * @param portfolioId 포트폴리오 아이디
     * @return List<FindLanguageDto> 반환
     */
    @Override
    public List<FindLanguageDto> findLanguage(Long portfolioId) {
        List<Language> findedLangs = languageReposirtory.findByPortfolioId(portfolioId);
        List<FindLanguageDto> findedLangDtoList = new ArrayList<>();
        for (Language findedLang : findedLangs) {
            FindLanguageDto findedLangDto = new FindLanguageDto();
            findedLangDto.setLangId(findedLang.getLanguageId());
            findedLangDto.setLangName(findedLang.getLangName());
            findedLangDto.setLangDetail(findedLang.getLangDetail());
            findedLangDto.setLangFrequency(findedLang.getLangFrequency());
            findedLangDtoList.add(findedLangDto);
        }

        return findedLangDtoList;
    }

}
