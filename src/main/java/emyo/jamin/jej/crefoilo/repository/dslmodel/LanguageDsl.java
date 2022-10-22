package emyo.jamin.jej.crefoilo.repository.dslmodel;

import java.util.List;

import com.querydsl.jpa.impl.JPAQuery;

import emyo.jamin.jej.crefoilo.entity.Language;

public interface LanguageDsl {
    List<Language> findByPortfolioId(Long portfolioId);
}
