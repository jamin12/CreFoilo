package emyo.jamin.jej.crefoilo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import emyo.jamin.jej.crefoilo.entity.Project;
import emyo.jamin.jej.crefoilo.repository.dslmodel.ProjectDsl;

@Repository
public interface ProejectRepository extends JpaRepository<Project, Long>, ProjectDsl {

}
