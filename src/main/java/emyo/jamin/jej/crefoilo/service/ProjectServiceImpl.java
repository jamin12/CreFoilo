package emyo.jamin.jej.crefoilo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import emyo.jamin.jej.crefoilo.dto.ProjectDto;
import emyo.jamin.jej.crefoilo.entity.Project;
import emyo.jamin.jej.crefoilo.repository.PortfolioRepository;
import emyo.jamin.jej.crefoilo.repository.ProejctRepository;
import emyo.jamin.jej.crefoilo.utils.Validation;

/**
 * @author 강경민
 */
@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProejctRepository proejctRepository;

    @Autowired
    private PortfolioRepository portfolioRepository;

    @Autowired
    private Validation validation;

    /**
     * 아래 코드는 setting page 작업입니다.
     */
    /**
     * 프로젝트 리스트 조회(앞면)
     * 
     * @param portfolioId
     * @param userId
     * @return List<ProjectDto>
     */
    @Override
    public List<ProjectDto> findProjectList(Long portfolioId, String userId) {
        validation.checkUserHasPortfolio(portfolioId, userId);
        List<Project> projectList = proejctRepository.findByportfolioId(portfolioId);
        List<ProjectDto> projectListDtos = new ArrayList<>();
        for (Project project : projectList) {
            projectListDtos.add(new ProjectDto(project));
        }
        return projectListDtos;
    }

    /**
     * 프로젝트 상세 페이지 조회
     * 
     * @param projectId
     */
    @Override
    public String findProjectDetail(Long projectId) {

        return null;
    }

}
