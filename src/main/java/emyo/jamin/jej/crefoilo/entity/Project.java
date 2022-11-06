package emyo.jamin.jej.crefoilo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import emyo.jamin.jej.crefoilo.dto.ProjectDetailDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "project")
public class Project extends BaseTimeEntity {

    @Id
    @Column(name = "project_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId; // '프로젝트 ID',

    @Column(name = "portfolio_id", nullable = false)
    private Long portfolioId; // '포트폴리오 ID',

    @Column(name = "project_representative_img_url", nullable = true, length = 255)
    private String projectRepresentativeImgUrl; // '대표 이미지 url',

    @Column(name = "project_title", nullable = true, length = 45)
    private String projectTitle; // '프로젝트 제목',

    @Column(name = "proejct_intro", nullable = true, length = 124)
    private String proejctIntro; // '프로젝트 한줄 설명',

    @Column(name = "project_str_date", nullable = true)
    private String projectStrDate; // '시작 날짜',

    @Column(name = "project_end_date", nullable = true)
    private String projectEndDate; // '마감 날짜',

    @Column(name = "project_sub_title", nullable = true, length = 45)
    private String projectSubTitle; // '서브 타이틀',

    @Column(name = "proejct_sub_intro", nullable = true, length = 255)
    private String proejctSubIntro; // '서브 설명',

    @Column(name = "project_md", nullable = true, columnDefinition = "TEXT")
    private String projectMd; // '프로젝트 md',

    @Column(name = "project_html", nullable = true, columnDefinition = "TEXT")
    private String projectHtml; // '프로젝트 html',

    /**
     * DTO를 CreateEntity로 변경
     * 
     * @param portfolioId
     * @param projectDetailDto
     * @return
     */
    public static Project toCreateEntity(Long portfolioId, ProjectDetailDto projectDetailDto) {
        return Project.builder()
                .portfolioId(portfolioId)
                .projectRepresentativeImgUrl(projectDetailDto.getProjectRepresentativeImgUrl())
                .projectTitle(projectDetailDto.getProjectTitle())
                .proejctIntro(projectDetailDto.getProejctIntro())
                .projectStrDate(projectDetailDto.getProjectStrDate())
                .projectEndDate(projectDetailDto.getProjectEndDate())
                .projectSubTitle(projectDetailDto.getProjectSubTitle())
                .proejctSubIntro(projectDetailDto.getProejctSubIntro())
                .projectHtml(projectDetailDto.getProjectHtml())
                .projectMd(projectDetailDto.getProjectMd())
                .build();
    }

    /**
     * DTO를 updateEntity로 변경
     * 
     * @param projectId
     * @param projectDetailDto
     * @return
     */
    public static Project toUpdateEntity(Long projectId, ProjectDetailDto projectDetailDto) {
        return Project.builder()
                .projectId(projectId)
                .portfolioId(projectDetailDto.getPortfolioId())
                .projectRepresentativeImgUrl(projectDetailDto.getProjectRepresentativeImgUrl())
                .projectTitle(projectDetailDto.getProjectTitle())
                .proejctIntro(projectDetailDto.getProejctIntro())
                .projectStrDate(projectDetailDto.getProjectStrDate())
                .projectEndDate(projectDetailDto.getProjectEndDate())
                .projectSubTitle(projectDetailDto.getProjectSubTitle())
                .proejctSubIntro(projectDetailDto.getProejctSubIntro())
                .projectHtml(projectDetailDto.getProjectHtml())
                .projectMd(projectDetailDto.getProjectMd())
                .build();
    }
}
