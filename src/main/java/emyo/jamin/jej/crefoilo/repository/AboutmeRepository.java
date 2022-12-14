package emyo.jamin.jej.crefoilo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import emyo.jamin.jej.crefoilo.entity.AboutMe;
import emyo.jamin.jej.crefoilo.repository.dslmodel.AboutmeDsl;

public interface AboutmeRepository extends JpaRepository<AboutMe, Long>, AboutmeDsl {

}
