package emyo.jamin.jej.crefoilo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long projectImgId; // '프로젝트 이미지 ID',

    @Column(name = "project_detail_id", nullable = false)
    private Long projectDetailId; // '프로젝트 상세 ID',

    @Column(name = "project_img_url", nullable = false, length = 255)
    private String projectImgUrl; // '프로젝트 이미지 url',
}
