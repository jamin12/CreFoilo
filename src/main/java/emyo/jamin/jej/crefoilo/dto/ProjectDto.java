package emyo.jamin.jej.crefoilo.dto;

import java.util.Date;

import emyo.jamin.jej.crefoilo.entity.Project;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author 강경민
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDto {
    private Long projectId;
    private String projectRepresentativeImgUrl;
    private String projectTitle;
    private String projectIntro;
    private String projectStartDate;
    private String projectEndDate;

    /**
     * 프로젝트 엔티티로 프로젝트 DTO 생성
     * 
     * @param proejctEntity
     */
    public ProjectDto(Project proejctEntity) {
        this.projectId = proejctEntity.getProjectId();
        this.projectRepresentativeImgUrl = proejctEntity.getProjectRepresentativeImgUrl();
        this.projectTitle = proejctEntity.getProjectTitle();
        this.projectIntro = proejctEntity.getProejctIntro();
        this.projectStartDate = proejctEntity.getProjectStrDate();
        this.projectEndDate = proejctEntity.getProjectEndDate();
    }
}
