package emyo.jamin.jej.crefoilo.dto;

import emyo.jamin.jej.crefoilo.entity.Language;
import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LanguageSettingDto {
    private Long langId;    // 언어 id
    private String langName;    // 언어 명
    private String langDetail;  // 언어 상세 설명
    private int langFrequency;  // 언어 진척도
    private String langSkillName;   // 언어 프레임워크 이름

    public LanguageSettingDto(Language language){
        this.langId = language.getLanguageId();
        this.langName = language.getLangName();
        this.langDetail = language.getLangDetail();
        this.langFrequency = language.getLangFrequency();
        this.langSkillName = language.getLangSkillName();
    }
}
