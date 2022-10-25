package emyo.jamin.jej.crefoilo.repository.dslmodel;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.querydsl.jpa.impl.JPAQueryFactory;

import emyo.jamin.jej.crefoilo.entity.Portfolio;
import emyo.jamin.jej.crefoilo.entity.QPortfolio;

/**
 * @author 강민진
 */
public class PortfolioDsllmpl implements PortfolioDsl {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    /**
     * 유저 아이디로 포트폴리오 목록 조회
     * 
     * @param userId 유저 아이디
     * @return List<Portfolio>
     */
    @Override
    public List<Portfolio> findByUserId(String userId) {
        return jpaQueryFactory.selectFrom(QPortfolio.portfolio)
                .where(QPortfolio.portfolio.userId.eq("152231594592157562165"))
                .fetch();
    }

}
