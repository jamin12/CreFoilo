package emyo.jamin.jej.crefoilo.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.Tuple;

import emyo.jamin.jej.crefoilo.dto.ProjectImgDto;
import emyo.jamin.jej.crefoilo.dto.ProjectDetailDto;
import emyo.jamin.jej.crefoilo.dto.ProjectDocumentDto;
import emyo.jamin.jej.crefoilo.dto.ProjectDto;
import emyo.jamin.jej.crefoilo.entity.DocumentUrl;
import emyo.jamin.jej.crefoilo.entity.Project;
import emyo.jamin.jej.crefoilo.entity.ProjectImg;
import emyo.jamin.jej.crefoilo.entity.QDocumentUrl;
import emyo.jamin.jej.crefoilo.entity.QProject;
import emyo.jamin.jej.crefoilo.entity.QProjectImg;
import emyo.jamin.jej.crefoilo.entity.QTechnicalStack;
import emyo.jamin.jej.crefoilo.entity.TechnicalStack;
import emyo.jamin.jej.crefoilo.repository.ProejectRepository;
import emyo.jamin.jej.crefoilo.repository.ProjectDocumentUrlRepository;
import emyo.jamin.jej.crefoilo.repository.ProjectImgRepository;
import emyo.jamin.jej.crefoilo.repository.ProjectTechnicalStackRepository;
import emyo.jamin.jej.crefoilo.utils.CustomException;
import emyo.jamin.jej.crefoilo.utils.ErrorCode;
import emyo.jamin.jej.crefoilo.utils.Validation;

/**
 * @author 강경민
 */
@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProejectRepository proejctRepository;

    @Autowired
    private ProjectImgRepository projectImgRepository;

    @Autowired
    private ProjectDocumentUrlRepository projectDocumentUrlRepository;

    @Autowired
    private ProjectTechnicalStackRepository projectTechnicalStackRepository;

    @Autowired
    private Validation validation;

    /**
     * 아래 코드는 view page 작업입니다.
     */

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
        // 유저가 포트폴리오를 가지고 있는지 체크
        validation.checkUserHasPortfolio(portfolioId, userId);
        List<Project> projectList = proejctRepository.findByportfolioId(portfolioId);
        List<ProjectDto> projectListDtos = new ArrayList<>();
        for (Project projectEntity : projectList) {
            projectListDtos.add(new ProjectDto(projectEntity));
        }
        return projectListDtos;
    }

    /**
     * 프로젝트 상세 페이지 조회
     * 
     * @param projectIdproejctRepository
     */
    @Override
    public ProjectDetailDto findProjectDetail(Long projectId, String userId) {
        QProject qProject = QProject.project;
        QTechnicalStack qTechnicalStack = QTechnicalStack.technicalStack;
        QDocumentUrl qDocumentUrl = QDocumentUrl.documentUrl1;
        QProjectImg qProjectImg = QProjectImg.projectImg;
        // 유저가 프로젝트를 가지고 있는지 체크
        validation.checkUserHasProject(projectId, userId);
        List<Tuple> findedProjectDetails = proejctRepository.findByprojectId(projectId);
        ProjectDetailDto projectDetailDto = new ProjectDetailDto(findedProjectDetails.get(0).get(qProject),
                findedProjectDetails.get(0).get(qTechnicalStack));
        // 중복되는 객체들 삭제
        HashSet<ProjectImgDto> projectImgDtos = new HashSet<>();
        HashSet<ProjectDocumentDto> projectDocumentDtos = new HashSet<>();
        for (Tuple projectDetail : findedProjectDetails) {
            projectImgDtos.add(new ProjectImgDto(projectDetail.get(qProjectImg)));
            projectDocumentDtos.add(new ProjectDocumentDto(projectDetail.get(qDocumentUrl)));
        }
        // stream 문법으로 set -> list 변경
        projectDetailDto.setProjectImg(projectImgDtos.stream().collect(Collectors.toList()));
        projectDetailDto.setProjectDocument(projectDocumentDtos.stream().collect(Collectors.toList()));
        return projectDetailDto;
    }

    /**
     * 프로젝트 생성
     * 
     * @param portfolioId
     * @param userId
     */
    @Override
    @Transactional
    public String createProject(Long portfolioId, String userId, ProjectDetailDto projectDetailDto) {
        validation.checkUserHasPortfolio(portfolioId, userId);
        Project createdProject = proejctRepository.save(Project.toCreateEntity(portfolioId, projectDetailDto));
        projectTechnicalStackRepository.save(
                TechnicalStack.toEntity(createdProject.getProjectId(), projectDetailDto.getProejctTechnicalStack()));
        projectDetailDto.getProjectImg().forEach(pImg -> {
            projectImgRepository.save(ProjectImg.toEntity(createdProject.getProjectId(), pImg));
        });
        projectDetailDto.getProjectDocument().forEach(pDoc -> {
            projectDocumentUrlRepository.save(DocumentUrl.toEntity(createdProject.getProjectId(), pDoc));
        });
        // TODO: return 수정하기
        return "loginSuccess";
    }

    /**
     * 프로젝트 삭제
     * 
     * @param projectId
     * @param userId
     */
    @Override
    @Transactional
    public String deleteProject(Long projectId, String userId) {
        validation.checkUserHasProject(projectId, userId);
        proejctRepository.delete(proejctRepository.findById(projectId).orElseThrow(
                () -> new CustomException(ErrorCode.INTERNAL_ERROR)));
        // TODO: return 수정하기
        return null;
    }

    /**
     * 프로젝트 업데이트
     * 
     * @param projectId
     * @param userId
     */
    @Override
    @Transactional
    public String updateProject(Long projectId, String userId, ProjectDetailDto projectDetailDto) {
        validation.checkUserHasProject(projectId, userId);
        proejctRepository.save(Project.toUpdateEntity(projectId, projectDetailDto));
        projectTechnicalStackRepository.save(
                TechnicalStack.toEntity(projectId, projectDetailDto.getProejctTechnicalStack()));
        projectDetailDto.getProjectImg().forEach(pImg -> {
            projectImgRepository.save(ProjectImg.toEntity(projectId, pImg));
        });
        projectDetailDto.getProjectDocument().forEach(pDoc -> {
            projectDocumentUrlRepository.save(DocumentUrl.toEntity(projectId, pDoc));
        });
        // TODO: return 수정하기
        return null;
    }

}
