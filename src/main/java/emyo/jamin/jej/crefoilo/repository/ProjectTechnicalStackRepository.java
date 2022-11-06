package emyo.jamin.jej.crefoilo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import emyo.jamin.jej.crefoilo.entity.TechnicalStack;

@Repository
public interface ProjectTechnicalStackRepository extends JpaRepository<TechnicalStack, Long> {

}
