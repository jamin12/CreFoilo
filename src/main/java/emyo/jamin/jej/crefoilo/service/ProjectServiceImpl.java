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
import emyo.jamin.jej.crefoilo.repository.ProejectRepository;
import emyo.jamin.jej.crefoilo.repository.ProjectDocumentUrlRepository;
import emyo.jamin.jej.crefoilo.repository.ProjectImgRepository;
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
    private Validation validation;

    /**
     * 아래 코드는 view page 작업입니다.
     */

    /**
     * view 페이지 프로젝트 상세 정보 조회
     *
     * @param projectId
     */
    @Override
    public ProjectDetailDto findProjectDetailView(Long projectId) {
        QProject qProject = QProject.project;
        QDocumentUrl qDocumentUrl = QDocumentUrl.documentUrl1;
        QProjectImg qProjectImg = QProjectImg.projectImg;
        List<Tuple> findedProjectDetails = proejctRepository.findByProjectId(projectId);
        if (findedProjectDetails.isEmpty()) {
            throw new CustomException(ErrorCode.PORTFOILO_NOT_FOUND);
        }
        ProjectDetailDto projectDetailDto = new ProjectDetailDto(findedProjectDetails.get(0).get(qProject));
        // 중복되는 객체들 삭제
        HashSet<ProjectImgDto> projectImgDtos = new HashSet<>();
        HashSet<ProjectDocumentDto> projectDocumentDtos = new HashSet<>();
        for (Tuple projectDetail : findedProjectDetails) {
            projectImgDtos.add(new ProjectImgDto(projectDetail.get(qProjectImg)));
            projectDocumentDtos.add(new ProjectDocumentDto(projectDetail.get(qDocumentUrl)));
        }
        // stream 문법으로 set -> list 변경 및 정렬
        projectDetailDto.setProjectImg(projectImgDtos.stream()
                .sorted((o1, o2) -> Long.valueOf(o1.getProjectImgId() - o2.getProjectImgId()).intValue())
                .collect(Collectors.toList()));
        projectDetailDto.setProjectDocument(projectDocumentDtos.stream()
                .sorted((o1, o2) -> Long.valueOf(o1.getDocuemntUrlId() - o2.getDocuemntUrlId()).intValue())
                .collect(Collectors.toList()));
        return projectDetailDto;
    }

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
        QDocumentUrl qDocumentUrl = QDocumentUrl.documentUrl1;
        QProjectImg qProjectImg = QProjectImg.projectImg;
        // 유저가 프로젝트를 가지고 있는지 체크
        validation.checkUserHasProject(projectId, userId);
        List<Tuple> findedProjectDetails = proejctRepository.findByProjectId(projectId);
        if (findedProjectDetails.isEmpty()) {
            throw new CustomException(ErrorCode.PORTFOILO_NOT_FOUND);
        }
        ProjectDetailDto projectDetailDto = new ProjectDetailDto(findedProjectDetails.get(0).get(qProject));
        // 중복되는 객체들 삭제
        HashSet<ProjectImgDto> projectImgDtos = new HashSet<>();
        HashSet<ProjectDocumentDto> projectDocumentDtos = new HashSet<>();
        for (Tuple projectDetail : findedProjectDetails) {
            if (projectDetail.get(qDocumentUrl) == null) {
                continue;
            }
            projectDocumentDtos.add(new ProjectDocumentDto(projectDetail.get(qDocumentUrl)));
        }
        for (Tuple projectDetail : findedProjectDetails) {
            if (projectDetail.get(qProjectImg) == null) {
                continue;
            }
            projectImgDtos.add(new ProjectImgDto(projectDetail.get(qProjectImg)));
        }
        // stream 문법으로 set -> list 변경 및 정렬
        projectDetailDto.setProjectImg(projectImgDtos.stream()
                .sorted((o1, o2) -> Long.valueOf(o1.getProjectImgId() - o2.getProjectImgId()).intValue())
                .collect(Collectors.toList()));
        projectDetailDto.setProjectDocument(projectDocumentDtos.stream()
                .sorted((o1, o2) -> Long.valueOf(o1.getDocuemntUrlId() - o2.getDocuemntUrlId()).intValue())
                .collect(Collectors.toList()));
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
    public void createProject(Long portfolioId, String userId, ProjectDetailDto projectDetailDto) {
        validation.checkUserHasPortfolio(portfolioId, userId);
        Project createdProject = proejctRepository.save(Project.toCreateEntity(portfolioId, projectDetailDto));
        projectDetailDto.getProjectImg().forEach(pImg -> {
            projectImgRepository.save(ProjectImg.toCreateEntity(createdProject.getProjectId(), pImg));
        });
        projectDetailDto.getProjectDocument().forEach(pDoc -> {
            projectDocumentUrlRepository.save(DocumentUrl.toCreateEntity(createdProject.getProjectId(), pDoc));
        });
    }

    /**
     * 프로젝트 삭제
     * 
     * @param projectId
     * @param userId
     */
    @Override
    @Transactional
    public void deleteProject(Long projectId, String userId) {
        validation.checkUserHasProject(projectId, userId);
        proejctRepository.delete(proejctRepository.findById(projectId).orElseThrow(
                () -> new CustomException(ErrorCode.INTERNAL_ERROR)));
    }

    /**
     * 프로젝트 업데이트
     * 
     * @param projectId
     * @param userId
     */
    @Override
    @Transactional
    public void updateProject(Long projectId, String userId, ProjectDetailDto projectDetailDto) {
        validation.checkUserHasProject(projectId, userId);
        proejctRepository.save(Project.toUpdateEntity(projectId, projectDetailDto));
        List<DocumentUrl> findedProejectDocList = projectDocumentUrlRepository.findbyProjectId(projectId);
        List<ProjectImg> findedProejectImgList = projectImgRepository.findbyProjectId(projectId);
        // 사라진 요소들 삭제하고
        for (ProjectImg projectImg : findedProejectImgList) {
            int flag = -1;
            for (ProjectImgDto pImg : projectDetailDto.getProjectImg()) {
                if (pImg.getProjectImgId() == null) {
                    continue;
                }
                if (pImg.getProjectImgId().equals(projectImg.getProjectImgId())) {
                    flag = 0;
                    break;
                }
            }
            if (flag == -1) {
                projectImgRepository.delete(projectImg);
            }
        }
        // 나머지 생성
        for (ProjectImgDto pImg : projectDetailDto.getProjectImg()) {
            projectImgRepository.save(ProjectImg.toUpdateEntity(projectId, pImg));
        }

        // 사라진 요소들 삭제하고
        for (DocumentUrl documentUrl : findedProejectDocList) {
            int flag = -1;
            for (ProjectDocumentDto pDoc : projectDetailDto.getProjectDocument()) {
                if (pDoc.getDocuemntUrlId() == null) {
                    continue;
                }
                if (pDoc.getDocuemntUrlId().equals(documentUrl.getDocumentId())) {
                    flag = 0;
                    break;
                }
            }
            if (flag == -1) {
                projectDocumentUrlRepository.delete(documentUrl);
            }
        }
        // 나머지 생성
        for (ProjectDocumentDto pDoc : projectDetailDto.getProjectDocument()) {
            projectDocumentUrlRepository.save(DocumentUrl.toUpdateEntity(projectId, pDoc));
        }
    }

    /**
     * 아래 코드는 프로젝트 view page의 작업입니다.
     */
    @Override
    public List<ProjectDetailDto> findProjectAll(Long portfolioId) {
        QProject qProject = QProject.project;
        QProjectImg qProjectImg = QProjectImg.projectImg;
        QDocumentUrl qDocumentUrl = QDocumentUrl.documentUrl1;

        List<ProjectDetailDto> findedProjectList = new ArrayList<>();
        List<Tuple> findedProjectAll = proejctRepository.findProjectAllByPortfolioId(portfolioId);
        if (findedProjectAll.isEmpty()) {
            return findedProjectList;
        }
        Long index = findedProjectAll.get(0).get(qProject).getProjectId();

        ProjectDetailDto projectDetailDto = new ProjectDetailDto();
        HashSet<ProjectImgDto> projectImgDtos = new HashSet<>();
        HashSet<ProjectDocumentDto> projectDocumentDtos = new HashSet<>();

        for (Tuple tuple : findedProjectAll) {

            if (tuple.get(qProject).getProjectId() != index) {
                index = tuple.get(qProject).getProjectId();
                projectDetailDto.setProjectImg(projectImgDtos.stream().collect(Collectors.toList()));
                projectDetailDto.setProjectDocument(projectDocumentDtos.stream().collect(Collectors.toList()));
                findedProjectList.add(projectDetailDto);

                projectImgDtos.clear();
                projectDocumentDtos.clear();

            }
            projectDetailDto = new ProjectDetailDto(tuple.get(qProject));
            projectImgDtos.add(new ProjectImgDto(tuple.get(qProjectImg)));
            projectDocumentDtos.add(new ProjectDocumentDto(tuple.get(qDocumentUrl)));
        }
        projectDetailDto.setProjectImg(projectImgDtos.stream().collect(Collectors.toList()));
        projectDetailDto.setProjectDocument(projectDocumentDtos.stream().collect(Collectors.toList()));
        findedProjectList.add(projectDetailDto);

        return findedProjectList;
    }

}
