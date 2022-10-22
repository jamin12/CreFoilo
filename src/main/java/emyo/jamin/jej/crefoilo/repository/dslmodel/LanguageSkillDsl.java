package emyo.jamin.jej.crefoilo.repository.dslmodel;

import java.util.List;

import emyo.jamin.jej.crefoilo.entity.LangSkill;

public interface LanguageSkillDsl {
    List<LangSkill> findByLangId(Long langId);
}
