package emyo.jamin.jej.crefoilo.repository.dslmodel;

import java.util.List;

import emyo.jamin.jej.crefoilo.entity.OtherSkill;

public interface OtherSkillDsl {
    List<OtherSkill> findByPortfolioId(Long portfolioId);
}
