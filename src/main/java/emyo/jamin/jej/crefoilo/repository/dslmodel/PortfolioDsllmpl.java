package emyo.jamin.jej.crefoilo.repository.dslmodel;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.querydsl.jpa.impl.JPAQueryFactory;

import emyo.jamin.jej.crefoilo.entity.Portfolio;
import emyo.jamin.jej.crefoilo.entity.QPortfolio;

public class PortfolioDsllmpl implements PortfolioDsl {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Portfolio> ByUserId() {
        return jpaQueryFactory.selectFrom(QPortfolio.portfolio).where(QPortfolio.portfolio.userId.eq("152231594592157562165")).fetch();
    }
    
}
