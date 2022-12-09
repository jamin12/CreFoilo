package emyo.jamin.jej.crefoilo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import emyo.jamin.jej.crefoilo.dto.LanguageSettingDto;
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
@Table(name = "language")
public class Language extends BaseTimeEntity {

    @Id
    @Column(name = "language_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long languageId; // '언어 ID',

    @Column(name = "portfolio_id", nullable = false)
    private Long portfolioId; // '포트폴리오 ID',

    @Column(name = "lang_name", length = 45, nullable = true)
    private String langName; // '언어 이름',

    @Column(name = "lang_detail", columnDefinition = "TEXT", nullable = true)
    private String langDetail; // '언어 배운점 설명',

    @Column(name = "lang_frequency", nullable = true)
    private Integer langFrequency; // '언어 숙련도',

    @Column(name = "lang_skill_name", nullable = true, length = 255)
    private String langSkillName; // '언어 기술 이름',


    /**
     * DTO를 CreateUpdateEntity로 변경
     */
    public static Language toCreateUpdateEntity(Long portfolioId, LanguageSettingDto languageSettingDto){
        LanguageBuilder languageBuilder = Language.builder()
            .portfolioId(portfolioId)
            .langName(languageSettingDto.getLangName())
            .langFrequency(languageSettingDto.getLangFrequency())
            .langSkillName(languageSettingDto.getLangSkillName())
            .langDetail(languageSettingDto.getLangDetail());
        
        if (languageSettingDto.getLangId() != null){
            languageBuilder.languageId(languageSettingDto.getLangId());
        }
        return languageBuilder.build();
    }

}
