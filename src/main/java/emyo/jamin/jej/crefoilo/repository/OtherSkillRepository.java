package emyo.jamin.jej.crefoilo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import emyo.jamin.jej.crefoilo.entity.OtherSkill;
import emyo.jamin.jej.crefoilo.repository.dslmodel.OtherSkillDsl;

public interface OtherSkillRepository extends JpaRepository<OtherSkill, Long>, OtherSkillDsl {

}
