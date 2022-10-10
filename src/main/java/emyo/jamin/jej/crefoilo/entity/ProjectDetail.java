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
@Table(name = "project_detail")
public class ProjectDetail extends BaseTimeEntity {

    @Id
    @Column(name = "project_detail_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long projectDetailId; // '프로젝트 상세 ID',

    @Column(name = "project_id", nullable = false)
    private Long projectId; // '프로젝트 ID',

    @Column(name = "project_detail_md", nullable = true, columnDefinition = "TEXT")
    private String projectDetailMd; // '프로젝트 상세 md',

    @Column(name = "project_detail_html", nullable = true, columnDefinition = "TEXT")
    private String projectDetailHtml; // '프로젝트 상세 html',

}
