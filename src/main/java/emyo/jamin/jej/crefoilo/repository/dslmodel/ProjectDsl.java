package emyo.jamin.jej.crefoilo.repository.dslmodel;

import java.util.List;

import com.querydsl.core.Tuple;

import emyo.jamin.jej.crefoilo.entity.Project;

public interface ProjectDsl {
    public List<Project> findByportfolioId(Long portfolioId);

    public List<Tuple> findByprojectId(Long projectId);

    public Tuple findPortfolioByProjectId(Long projectId, String userId);
}
