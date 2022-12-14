package emyo.jamin.jej.crefoilo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import emyo.jamin.jej.crefoilo.dto.ProjectImgDto;
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
@Table(name = "project_img")
public class ProjectImg extends BaseTimeEntity {

    @Id
    @Column(name = "project_img_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectImgId; // '프로젝트 이미지 ID',

    @Column(name = "project_id", nullable = false)
    private Long projectId; // '프로젝트 ID',

    @Column(name = "project_img_url", nullable = false, length = 255)
    private String projectImgUrl; // '프로젝트 이미지 url',

    /**
     * DTO를 createEntity로 변경
     * 
     * @param proejctId
     * @param proejectImgDto
     * @return
     */
    public static ProjectImg toCreateEntity(Long proejctId, ProjectImgDto proejectImgDto) {
        return ProjectImg.builder()
                .projectId(proejctId)
                .projectImgUrl(proejectImgDto.getProjectImgUrl())
                .build();
    }

    /**
     * DTO를 updateEntity로 변경
     * 
     * @param proejctId
     * @param proejectImgDto
     * @return
     */
    public static ProjectImg toUpdateEntity(Long proejctId, ProjectImgDto proejectImgDto) {
        ProjectImgBuilder projectImgBuilder = ProjectImg.builder()
                .projectId(proejctId)
                .projectImgUrl(proejectImgDto.getProjectImgUrl());
        if (proejectImgDto.getProjectImgId() != null) {
            projectImgBuilder.projectImgId(proejectImgDto.getProjectImgId());
        }

        return projectImgBuilder.build();
    }
}
