package emyo.jamin.jej.crefoilo.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PortfolioDto {
    private String portfolioName;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private Long portfolioId;
}
