package emyo.jamin.jej.crefoilo.repository.dslmodel;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.querydsl.jpa.impl.JPAQueryFactory;

import emyo.jamin.jej.crefoilo.entity.OtherSkill;
import emyo.jamin.jej.crefoilo.entity.QOtherSkill;

public class OtherSkillDslImpl implements OtherSkillDsl {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    public OtherSkillDslImpl(EntityManager em) {
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    /**
     * 포트폴리오 아이디로 OtherSkill 리스트 조회
     * 
     * @param portfolioId
     * @return List<OtherSkill>
     */
    @Override
    public List<OtherSkill> findByPortfolioId(Long portfolioId) {
        QOtherSkill qOtherSkill = QOtherSkill.otherSkill;
        return jpaQueryFactory.selectFrom(qOtherSkill)
                .where(qOtherSkill.portfolioId.eq(portfolioId))
                .fetch();
    }

}
