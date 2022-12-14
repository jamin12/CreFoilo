package emyo.jamin.jej.crefoilo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import emyo.jamin.jej.crefoilo.entity.Project;
import emyo.jamin.jej.crefoilo.repository.dslmodel.ProjectDsl;

public interface ProejectRepository extends JpaRepository<Project, Long>, ProjectDsl {

}
