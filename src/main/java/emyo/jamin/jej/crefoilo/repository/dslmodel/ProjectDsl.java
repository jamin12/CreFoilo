package emyo.jamin.jej.crefoilo.repository.dslmodel;

import java.util.List;

import emyo.jamin.jej.crefoilo.entity.Project;

public interface ProjectDsl {
    public List<Project> findByportfolioId(Long portfolioId);

    public String findByprojectId(Long projectId);
}
