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
@Table(name = "other_skill")
public class OtherSkill extends BaseTimeEntity {

    @Id
    @Column(name = "other_skill_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long otherSkillID; // '그 외 스킬 ID',

    @Column(name = "base_other_skill_id", nullable = true)
    private Long baseOtherSkillId; // '상위 그 외 스킬 ID',

    @Column(name = "portfolio_id", nullable = false)
    private Long portfolioId; // '포트폴리오 ID',

    @Column(name = "other_skill_name", nullable = true, length = 30)
    private String otherSkillName; // '그 외 스킬 이름',
}
