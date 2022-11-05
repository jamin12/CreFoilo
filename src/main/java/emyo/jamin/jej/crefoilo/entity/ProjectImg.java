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

    @Column(name = "project_img_sequence", nullable = false)
    private Integer projectImgSequence; // '프로젝트 이미지 url',

    /**
     * DTO를 entity로 변경
     * 
     * @param proejctId
     * @param proejectImgDto
     * @return
     */
    public static ProjectImg toEntity(Long proejctId, ProjectImgDto proejectImgDto) {
        return ProjectImg.builder()
                .projectId(proejctId)
                .projectImgUrl(proejectImgDto.getProjectImgUrl())
                .projectImgSequence(proejectImgDto.getProjectImgSequence())
                .build();
    }
}
