package emyo.jamin.jej.crefoilo.dto;

import emyo.jamin.jej.crefoilo.entity.OtherSkill;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OtherSkillSubList {
    private Long otherSkillSubId;
    private Long otherSkillBaseId;
    private String otherSkillSubName;

    public OtherSkillSubList(OtherSkill otherSkill) {
        this.otherSkillSubId = otherSkill.getOtherSkillID();
        this.otherSkillBaseId = otherSkill.getBaseOtherSkillId();
        this.otherSkillSubName = otherSkill.getOtherSkillName();
    }
}
