package emyo.jamin.jej.crefoilo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import emyo.jamin.jej.crefoilo.dto.FindLanguageDto;
import emyo.jamin.jej.crefoilo.dto.LanguageSettingDto;
import emyo.jamin.jej.crefoilo.entity.Language;
import emyo.jamin.jej.crefoilo.repository.LanguageReposirtory;
import emyo.jamin.jej.crefoilo.utils.Validation;

/**
 * @author 강경민
 */
@Service
public class LanguageServiceImpl implements LanguageService {
    @Autowired
    private LanguageReposirtory languageReposirtory;

    @Autowired
    private Validation validation;

    /**
     * 아래 코드는 view page 작업입니다.
     */

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

    /**
     * language skill 조회
     */
    @Override
    public List<LanguageSettingDto> findLanguageList(Long portfolioId, String userId) {
        validation.checkUserHasPortfolio(portfolioId, userId);
        List<Language> findedLanguages = languageReposirtory.findByPortfolioId(portfolioId);

        List<LanguageSettingDto> languageList = new ArrayList<>();
        findedLanguages.forEach((findedLanguage) -> {
            languageList.add(new LanguageSettingDto(findedLanguage));
        });

        return languageList;
    }

    /**
     * language skill 추가 수정
     */
    @Override
    @Transactional
    public void CUDLanguage(List<LanguageSettingDto> languageSettingDtoList, Long portfolioId, String userId) {
        validation.checkUserHasPortfolio(portfolioId, userId);
        List<Language> findedLanguageSkill = languageReposirtory.findByPortfolioId(portfolioId);

        // 있던게 없어지면 삭제처리
        for (Language language : findedLanguageSkill) {
            int flag = 0;
            for (LanguageSettingDto languageSettingDto : languageSettingDtoList) {
                if (languageSettingDto.getLangId() != null) {
                    if (language.getLanguageId().equals(languageSettingDto.getLangId())) {
                        flag = 1;
                        break;
                    }
                }
            }
            if (flag == 0) {
                languageReposirtory.delete(language);
            }
        }

        for (LanguageSettingDto languageSettingDto : languageSettingDtoList) {
            languageReposirtory.save(Language.toCreateUpdateEntity(portfolioId, languageSettingDto));
        }

    }
}
