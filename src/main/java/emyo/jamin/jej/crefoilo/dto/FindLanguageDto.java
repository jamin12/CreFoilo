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
public class FindLanguageDto {
    private Long langId;
    private String langName;
    private String langDetail;
    private int langFrequency;
    private List<String> langSkillName;
}
