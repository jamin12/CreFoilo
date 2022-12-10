package emyo.jamin.jej.crefoilo.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OtherSkillBaseList {
    private Long otherSkillBaseId;
    private String otherSkillBaseName;
    private List<OtherSkillSubList> otherSkillSubList;
}
