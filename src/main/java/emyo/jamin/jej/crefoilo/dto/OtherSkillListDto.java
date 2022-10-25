package emyo.jamin.jej.crefoilo.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OtherSkillListDto {
    private String baseOtherSkillName;
    private List<String> subOtherSkillName;
}
