package emyo.jamin.jej.crefoilo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import emyo.jamin.jej.crefoilo.entity.LangSkill;
import emyo.jamin.jej.crefoilo.repository.dslmodel.LanguageSkillDsl;

public interface LanguageSkillRepository extends JpaRepository<LangSkill, Long>, LanguageSkillDsl {

}
