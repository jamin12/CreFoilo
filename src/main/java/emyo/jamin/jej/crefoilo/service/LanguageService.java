package emyo.jamin.jej.crefoilo.service;

import java.util.List;

import emyo.jamin.jej.crefoilo.dto.FindLanguageDto;

public interface LanguageService {
    List<FindLanguageDto> findLanguage(Long portfolioId);
}
