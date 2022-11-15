package emyo.jamin.jej.crefoilo.dto;

import java.util.List;

import org.springframework.lang.Nullable;

import emyo.jamin.jej.crefoilo.entity.Project;
import emyo.jamin.jej.crefoilo.entity.TechnicalStack;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDetailDto {
    private Long projectId; // '프로젝트 ID',
    private Long portfolioId; // '포트폴리오 ID',
    private String projectRepresentativeImgUrl; // '대표 이미지 url',
    private String projectTitle; // '프로젝트 제목',
    private String proejctIntro; // '프로젝트 한줄 설명',
    private String projectStrDate; // '시작 날짜',
    private String projectEndDate; // '마감 날짜',
    private String projectSubTitle; // '서브 타이틀',
    private String proejctSubIntro; // '서브 설명',
    private String projectMd; // '프로젝트 md',
    private String projectHtml; // '프로젝트 html',
    private String proejctTechnicalStack;   // '기술 스텍'
    private List<ProjectImgDto> projectImg; // '프로젝트 이미지 리스트'
    private List<ProjectDocumentDto> projectDocument;   // '프로젝트 링크'

    public ProjectDetailDto(@Nullable Project project,
            @Nullable TechnicalStack technicalStack) {
        this.projectId = project.getProjectId();
        this.portfolioId = project.getPortfolioId();
        this.projectTitle = project.getProjectTitle();
        this.proejctIntro = project.getProejctIntro();
        this.projectSubTitle = project.getProjectSubTitle();
        this.proejctSubIntro = project.getProejctSubIntro();
        this.projectStrDate = project.getProjectStrDate();
        this.projectEndDate = project.getProjectEndDate();
        this.projectHtml = project.getProjectHtml();
        this.projectMd = project.getProjectMd();
        this.proejctTechnicalStack = technicalStack.getTechnicalStackName();
    }

}
