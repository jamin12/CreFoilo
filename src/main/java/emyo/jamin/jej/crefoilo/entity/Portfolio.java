package emyo.jamin.jej.crefoilo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import emyo.jamin.jej.crefoilo.dto.PortfolioHomeDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "portfolio")
public class Portfolio extends BaseTimeEntity {

    @Id
    @Column(name = "portfolio_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long portfolioId; // '포트폴리오 ID',

    @Column(name = "user_id", nullable = false, length = 255)
    private String userId; // '회원 ID',

    @Column(name = "portfolio_name", length = 45, nullable = false)
    private String portfolioName; // '포트폴리오 이름

    @Column(name = "portfolio_home_type", nullable = false)
    private Integer portfolioHomeType; // '홈 화면 type',

    @Column(name = "portfolio_home_text", nullable = false, length = 124)
    private String portfolioHomeText; // '홈화면 text',

    @Column(name = "portfolio_home_img", nullable = true, length = 255)
    private String portfolioHomeImg; // '홈화면 img',

    public static Portfolio createPortfolioEntity(PortfolioHomeDto portfolioHomeDto, String userId) {
        PortfolioBuilder portfolioBulider = Portfolio.builder()
                .userId(userId)
                .portfolioName(portfolioHomeDto.getPortfolioName())
                .portfolioHomeType(portfolioHomeDto.getPortfolioType())
                .portfolioHomeText(portfolioHomeDto.getPortfolioHomeText())
                .portfolioHomeImg(portfolioHomeDto.getPortfolioImgUrl());
        if (portfolioHomeDto.getPortfolioId() != null) {
            portfolioBulider.portfolioId(portfolioHomeDto.getPortfolioId());
        }
        return portfolioBulider.build();
    }
}
