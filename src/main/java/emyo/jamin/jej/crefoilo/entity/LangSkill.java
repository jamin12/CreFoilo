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
@Table(name = "lang_skill")
public class LangSkill extends BaseTimeEntity {

    @Id
    @Column(name = "lang_skill_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long langSkillId; // '언어 기술 ID',

    @Column(name = "language_id", nullable = false)
    private Long languageId; // '언어 ID',

    @Column(name = "lang_skill_name", nullable = true, length = 255)
    private String langSkillName; // '언어 기술 이름',
}
