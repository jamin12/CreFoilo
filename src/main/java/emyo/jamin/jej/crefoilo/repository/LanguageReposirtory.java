package emyo.jamin.jej.crefoilo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import emyo.jamin.jej.crefoilo.entity.Language;
import emyo.jamin.jej.crefoilo.repository.dslmodel.LanguageDsl;

@Repository
public interface LanguageReposirtory extends JpaRepository<Language, Long>, LanguageDsl {

}
