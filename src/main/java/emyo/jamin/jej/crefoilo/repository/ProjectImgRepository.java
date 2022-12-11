package emyo.jamin.jej.crefoilo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import emyo.jamin.jej.crefoilo.entity.ProjectImg;
import emyo.jamin.jej.crefoilo.repository.dslmodel.ProjectImgRepositoryDsl;

@Repository
public interface ProjectImgRepository extends JpaRepository<ProjectImg, Long>, ProjectImgRepositoryDsl {

}
