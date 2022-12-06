package emyo.jamin.jej.crefoilo.repository.dslmodel;

import java.util.List;

import emyo.jamin.jej.crefoilo.entity.Language;

public interface LanguageDsl {
    List<Language> findByPortfolioId(Long portfolioId);


}
