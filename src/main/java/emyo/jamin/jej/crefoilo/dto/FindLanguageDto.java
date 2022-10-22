package emyo.jamin.jej.crefoilo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FindLanguageDto {
    Long langId;
    String langName;
    String langDetail;
    int langFrequency;
}
