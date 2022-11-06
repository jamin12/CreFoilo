package emyo.jamin.jej.crefoilo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import emyo.jamin.jej.crefoilo.dto.ProjectDocumentDto;
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
@Table(name = "document_url")
public class DocumentUrl extends BaseTimeEntity {

    @Id
    @Column(name = "document_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long documentId; // '문서 id',

    @Column(name = "project_id", nullable = false)
    private Long projectId; // '프로젝트 ID',

    @Column(name = "document_img_url", nullable = true, length = 255)
    private String documentImgUrl; // 문서 이미지 url

    @Column(name = "document_url", nullable = true, length = 255)
    private String documentUrl; // '문서 주소',

    /**
     * DTO를 createEntity로 변경
     * 
     * @param proejctid
     * @param projectDocumentDto
     * @return
     */
    public static DocumentUrl toCreateEntity(Long proejctid, ProjectDocumentDto projectDocumentDto) {
        return DocumentUrl.builder()
                .projectId(proejctid)
                .documentImgUrl(projectDocumentDto.getDocumentImgUrl())
                .documentUrl(projectDocumentDto.getDocumentUrl())
                .build();
    }

    /**
     * DTO를 updateEntity로 변경
     * 
     * @param proejctid
     * @param projectDocumentDto
     * @return
     */
    public static DocumentUrl toUpdateEntity(Long proejctid, ProjectDocumentDto projectDocumentDto) {
        return DocumentUrl.builder()
                .documentId(projectDocumentDto.getDocuemntUrlId())
                .projectId(proejctid)
                .documentImgUrl(projectDocumentDto.getDocumentImgUrl())
                .documentUrl(projectDocumentDto.getDocumentUrl())
                .build();
    }
}
