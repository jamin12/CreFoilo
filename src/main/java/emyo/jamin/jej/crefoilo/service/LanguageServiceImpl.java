package emyo.jamin.jej.crefoilo.service;

import java.util.ArrayList;
import java.util.Arrays;
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
     * 아래 코드는 view page 작업입니다.
     */

    /**
     * portfolio의 Language 페이지 language, language skill 조회
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
            findedLangDto.setLangDetail(Arrays.asList(findedLang.getLangDetail().split("/n")));
            findedLangDto.setLangFrequency(findedLang.getLangFrequency());
            findedLangDto.setLangSkillName(Arrays.asList(findedLang.getLangSkillName().split("/n")));
            findedLangDtoList.add(findedLangDto);
        }

        return findedLangDtoList;
    }

    /**
     * 아래 코드는 setting page 작업입니다.
     */

}
