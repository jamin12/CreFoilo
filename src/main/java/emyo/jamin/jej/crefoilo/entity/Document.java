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
@Table(name = "document")
public class Document extends BaseTimeEntity {

    @Id
    @Column(name = "document_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long documentId; // '문서 id',

    @Column(name = "project_id", nullable = false)
    private Long projectId; // '프로젝트 ID',

    @Column(name = "document_img_url", nullable = true, length = 255)
    private String documentImgUrl; // 문서 이미지 url

    @Column(name = "document_url", nullable = true, length = 255)
    private String documentUrl; // '문서 주소',
}
