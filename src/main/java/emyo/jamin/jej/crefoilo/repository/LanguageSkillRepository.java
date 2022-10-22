package emyo.jamin.jej.crefoilo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import emyo.jamin.jej.crefoilo.entity.LangSkill;
import emyo.jamin.jej.crefoilo.repository.dslmodel.LanguageSkillDsl;

@Repository
public interface LanguageSkillRepository extends JpaRepository<LangSkill, Long>, LanguageSkillDsl {

}
