package emyo.jamin.jej.crefoilo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import emyo.jamin.jej.crefoilo.dto.FindLanguageDto;
import emyo.jamin.jej.crefoilo.entity.LangSkill;
import emyo.jamin.jej.crefoilo.entity.Language;
import emyo.jamin.jej.crefoilo.repository.LanguageReposirtory;
import emyo.jamin.jej.crefoilo.repository.LanguageSkillRepository;

/**
 * @author 강경민
 */
@Service
public class LanguageServiceImpl implements LanguageService {
    @Autowired
    private LanguageReposirtory languageReposirtory;
    @Autowired
    private LanguageSkillRepository languageReposirtorySkillRepository;

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
            List<String> langSkillNames = new ArrayList<>();

            findedLangDto.setLangId(findedLang.getLanguageId());
            findedLangDto.setLangName(findedLang.getLangName());
            findedLangDto.setLangDetail(findedLang.getLangDetail());
            findedLangDto.setLangFrequency(findedLang.getLangFrequency());

            List<LangSkill> findedLangSkills = languageReposirtorySkillRepository
                    .findByLangId(findedLang.getLanguageId());
            for (LangSkill findedLangSkill : findedLangSkills) {
                langSkillNames.add(findedLangSkill.getLangSkillName());
            }
            findedLangDto.setLangSkillName(langSkillNames);
            findedLangDtoList.add(findedLangDto);
        }

        return findedLangDtoList;
    }

    /**
     * 아래 코드는 setting page 작업입니다.
     */

}
