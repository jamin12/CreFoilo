package emyo.jamin.jej.crefoilo.service;

import java.util.List;

import emyo.jamin.jej.crefoilo.dto.FindLanguageDto;
import emyo.jamin.jej.crefoilo.dto.LanguageSettingDto;

public interface LanguageService {
    List<FindLanguageDto> findLanguage(Long portfolioId);

    List<LanguageSettingDto> findLanguageList(Long portfolioId, String userId);

    void CUDLanguage(List<LanguageSettingDto> languageSettingDtoList, Long portfolioId, String userId);
}