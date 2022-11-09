package emyo.jamin.jej.crefoilo.repository.dslmodel;

import javax.persistence.EntityManager;

import com.querydsl.jpa.impl.JPAQueryFactory;

import emyo.jamin.jej.crefoilo.entity.AboutMe;
import emyo.jamin.jej.crefoilo.entity.QAboutMe;

/**
 * @author 강경민
 */
public class AboutmeDslImpl implements AboutmeDsl {
    private JPAQueryFactory jpaQueryFactory;

    public AboutmeDslImpl(EntityManager em) {
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    /**
     * 포트폴리오 아이디로 about me 조회
     * 
     * @param portfolioId
     */
    @Override
    public AboutMe findByPortfolioId(Long portfolioId) {
        QAboutMe qAboutMe = QAboutMe.aboutMe;
        return jpaQueryFactory.selectFrom(qAboutMe)
                .where(qAboutMe.portfolioId.eq(portfolioId))
                .fetchOne();
    }

}
