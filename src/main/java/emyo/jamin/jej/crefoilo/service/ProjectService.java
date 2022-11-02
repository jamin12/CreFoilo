package emyo.jamin.jej.crefoilo.service;

import java.util.List;

import emyo.jamin.jej.crefoilo.dto.ProjectDto;

public interface ProjectService {
    List<ProjectDto> findProjectList(Long portfolioId, String user_id);

    String findProjectDetail(Long projectId);
}
