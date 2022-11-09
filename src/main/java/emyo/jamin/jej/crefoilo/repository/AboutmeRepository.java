package emyo.jamin.jej.crefoilo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import emyo.jamin.jej.crefoilo.entity.AboutMe;
import emyo.jamin.jej.crefoilo.repository.dslmodel.AboutmeDsl;

@Repository
public interface AboutmeRepository extends JpaRepository<AboutMe, Long>, AboutmeDsl {

}
