package emyo.jamin.jej.crefoilo.service;

import java.util.List;

import emyo.jamin.jej.crefoilo.dto.ProjectDetailDto;
import emyo.jamin.jej.crefoilo.dto.ProjectDto;

public interface ProjectService {
    List<ProjectDto> findProjectList(Long portfolioId, String userId);

    ProjectDetailDto findProjectDetail(Long projectId, String userId);

    void createProject(Long portfolioId, String userId, ProjectDetailDto projectDetailDto);

    void deleteProject(Long projectId, String userId);

    void updateProject(Long projectId, String userId, ProjectDetailDto projectDetailDto);

    ProjectDetailDto findProjectDetailView(Long projectId);

    List<ProjectDetailDto> findProjectAll(Long portfolioId);
}
