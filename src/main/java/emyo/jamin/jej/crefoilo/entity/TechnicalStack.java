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
@Table(name = "technical_stack")
public class TechnicalStack extends BaseTimeEntity {

    @Id
    @Column(name = "technical_stack_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long technicalStackId; // '기술 스택 ID',

    @Column(name = "project_id", nullable = false)
    private Long projectId; // '프로젝트 ID',

    @Column(name = "technical_stack_name", nullable = false, length = 45)
    private String technicalStackName; // '기술 스택 이름',

    public static TechnicalStack toEntity(Long projectId, String technicalStackName) {
        return TechnicalStack.builder()
                .projectId(projectId)
                .technicalStackName(technicalStackName)
                .build();
    }

}
