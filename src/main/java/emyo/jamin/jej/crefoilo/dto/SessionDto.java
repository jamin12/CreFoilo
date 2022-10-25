package emyo.jamin.jej.crefoilo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SessionDto {
    private String userId;
    private String snsType;
    private String snsName;
    private String userEmail;
}
