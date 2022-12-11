package emyo.jamin.jej.crefoilo.repository.dslmodel;

import java.util.List;

import emyo.jamin.jej.crefoilo.entity.DocumentUrl;

public interface ProjectDocumentUrlRepositoryDsl {
    List<DocumentUrl> findbyProjectId(Long ProjectId);
}
