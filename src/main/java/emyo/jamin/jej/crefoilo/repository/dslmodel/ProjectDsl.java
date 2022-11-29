package emyo.jamin.jej.crefoilo.repository.dslmodel;

import java.util.List;

import com.querydsl.core.Tuple;

import emyo.jamin.jej.crefoilo.entity.DocumentUrl;
import emyo.jamin.jej.crefoilo.entity.Project;
import emyo.jamin.jej.crefoilo.entity.ProjectImg;
import emyo.jamin.jej.crefoilo.entity.TechnicalStack;

public interface ProjectDsl {
    public List<Project> findByportfolioId(Long portfolioId);

    public List<Tuple> findByProjectId(Long projectId);

    public Tuple findPortfolioByProjectId(Long projectId, String userId);

    public List<Tuple> findProjectAllByPortfolioId(Long portfolioId);

}
