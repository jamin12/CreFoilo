package emyo.jamin.jej.crefoilo.service;

import java.util.List;
import java.util.Optional;

import emyo.jamin.jej.crefoilo.dto.FindLanguageDto;

public interface LanguageService {
    List<FindLanguageDto> findLanguage(Long portfolioId);

    void findLanguageSkill(Long langId);
}
