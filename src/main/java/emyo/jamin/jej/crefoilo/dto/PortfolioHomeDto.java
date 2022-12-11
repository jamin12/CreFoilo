package emyo.jamin.jej.crefoilo.dto;

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
public class PortfolioHomeDto {
    private Long portfolioId;
    private String portfolioName;
    private String portfolioHomeText;
    private String portfolioImgUrl;
    private Integer portfolioType;
}
