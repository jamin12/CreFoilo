package emyo.jamin.jej.crefoilo.service;

import java.util.List;

import emyo.jamin.jej.crefoilo.dto.ProjectDetailDto;
import emyo.jamin.jej.crefoilo.dto.ProjectDto;

public interface ProjectService {
    List<ProjectDto> findProjectList(Long portfolioId, String userId);

    ProjectDetailDto findProjectDetail(Long projectId, String userId);

    String createProject(Long portfolioId, String userId, ProjectDetailDto projectDetailDto);

    String deleteProject(Long projectId, String userId);

    String updateProject(Long projectId, String userId, ProjectDetailDto projectDetailDto);
}
