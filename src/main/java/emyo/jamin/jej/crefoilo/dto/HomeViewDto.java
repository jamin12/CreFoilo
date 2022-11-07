package emyo.jamin.jej.crefoilo.dto;

import emyo.jamin.jej.crefoilo.entity.Portfolio;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HomeViewDto {
    private Long portfolioId;
    private String userId;
    private String portfolioName;
    private Integer portfolioHomeType;
    private String portfolioHomeImg;
    private String portfolioHomeText;
    private String portfolioHomeColor;

    public HomeViewDto(Portfolio portfolio) {
        this.portfolioId = portfolio.getPortfolioId();
        this.userId = portfolio.getUserId();
        this.portfolioName = portfolio.getPortfolioName();
        this.portfolioHomeType = portfolio.getPortfolioHomeType();
        this.portfolioHomeImg = portfolio.getPortfolioHomeImg();
        this.portfolioHomeText = portfolio.getPortfolioHomeText();
        this.portfolioHomeColor = portfolio.getPortfolioHomeColor();
    }

}
