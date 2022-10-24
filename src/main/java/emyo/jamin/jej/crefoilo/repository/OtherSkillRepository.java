package emyo.jamin.jej.crefoilo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import emyo.jamin.jej.crefoilo.entity.OtherSkill;
import emyo.jamin.jej.crefoilo.repository.dslmodel.OtherSkillDsl;

@Repository
public interface OtherSkillRepository extends JpaRepository<OtherSkill, Long>, OtherSkillDsl {

}
