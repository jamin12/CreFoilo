package emyo.jamin.jej.crefoilo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import emyo.jamin.jej.crefoilo.entity.Language;
import emyo.jamin.jej.crefoilo.repository.dslmodel.LanguageDsl;

public interface LanguageReposirtory extends JpaRepository<Language, Long>, LanguageDsl {

}
