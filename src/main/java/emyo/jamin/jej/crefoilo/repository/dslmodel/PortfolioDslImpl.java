package emyo.jamin.jej.crefoilo.repository.dslmodel;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.lang.Nullable;

import com.querydsl.jpa.impl.JPAQueryFactory;

import emyo.jamin.jej.crefoilo.entity.Portfolio;
import emyo.jamin.jej.crefoilo.entity.QPortfolio;

public class PortfolioDslImpl implements PortfolioDsl {
    private JPAQueryFactory jpaQueryFactory;

    public PortfolioDslImpl(EntityManager em) {
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    /**
     * 유저 아이디로 포트폴리오 목록 조회
     * 
     * @param userId 유저 아이디
     * @return List<Portfolio>
     */
    @Override
    public List<Portfolio> findByUserId(String userId) {
        QPortfolio qPortfolio = QPortfolio.portfolio;
        return jpaQueryFactory.selectFrom(qPortfolio)
                .where(qPortfolio.userId.eq(userId))
                .fetch();
    }

    /**
     * 포트폴리오 아이디로 홈 화면 조회
     * 
     * @param portfolioId
     */
    @Override
    public @Nullable Portfolio findHomeByPortfolioId(Long portfolioId) {
        QPortfolio qPortfolio = QPortfolio.portfolio;
        return jpaQueryFactory.selectFrom(qPortfolio)
                .where(qPortfolio.portfolioId.eq(portfolioId))
                .fetchOne();
    }

}
