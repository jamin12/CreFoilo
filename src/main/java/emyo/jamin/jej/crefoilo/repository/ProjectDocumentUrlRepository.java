package emyo.jamin.jej.crefoilo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import emyo.jamin.jej.crefoilo.entity.DocumentUrl;
import emyo.jamin.jej.crefoilo.repository.dslmodel.ProjectDocumentUrlRepositoryDsl;

public interface ProjectDocumentUrlRepository
        extends JpaRepository<DocumentUrl, Long>, ProjectDocumentUrlRepositoryDsl {

}
