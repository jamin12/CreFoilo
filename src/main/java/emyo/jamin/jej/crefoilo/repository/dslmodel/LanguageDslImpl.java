package emyo.jamin.jej.crefoilo.repository.dslmodel;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.querydsl.jpa.impl.JPAQueryFactory;

import emyo.jamin.jej.crefoilo.entity.Language;
import emyo.jamin.jej.crefoilo.entity.QLangSkill;
import emyo.jamin.jej.crefoilo.entity.QLanguage;
import emyo.jamin.jej.crefoilo.entity.QPortfolio;

/**
 * @author 강경민
 */
public class LanguageDslImpl implements LanguageDsl {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    public LanguageDslImpl(EntityManager em) {
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    /**
     * 포트폴리오 아이디로 lang 조회
     * 
     * @param portfolioId
     * @return List<Language>
     */
    @Override
    public List<Language> findByPortfolioId(Long portfolioId) {
        QLanguage qLang = QLanguage.language;
        QPortfolio qPortfolio = QPortfolio.portfolio;
        return jpaQueryFactory.select(qLang).from(qLang)
                .leftJoin(qPortfolio).on(qLang.portfolioId.eq(qPortfolio.portfolioId))
                .where(qLang.portfolioId.eq(portfolioId))
                .fetch();
    }

}
