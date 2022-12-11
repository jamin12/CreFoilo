package emyo.jamin.jej.crefoilo.repository.dslmodel;

import java.util.List;

import emyo.jamin.jej.crefoilo.entity.ProjectImg;

public interface ProjectImgRepositoryDsl {
    List<ProjectImg> findbyProjectId(Long projectId);
}
