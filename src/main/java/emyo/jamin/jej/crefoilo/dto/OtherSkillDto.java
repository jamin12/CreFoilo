package emyo.jamin.jej.crefoilo.dto;

import emyo.jamin.jej.crefoilo.entity.OtherSkill;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OtherSkillDto {
    private Long otherSkillID; // '그 외 스킬 ID',

    private Long baseOtherSkillId; // '상위 그 외 스킬 ID',

    private Long portfolioId; // '포트폴리오 ID',

    private String otherSkillName; // '그 외 스킬 이름',

    public OtherSkillDto(OtherSkill otherSkill) {
        this.portfolioId = otherSkill.getPortfolioId();
        this.otherSkillID = otherSkill.getOtherSkillID();
        this.otherSkillName = otherSkill.getOtherSkillName();
        if (otherSkill.getBaseOtherSkillId() != null) {
            this.baseOtherSkillId = otherSkill.getBaseOtherSkillId();
        }
    }
}
