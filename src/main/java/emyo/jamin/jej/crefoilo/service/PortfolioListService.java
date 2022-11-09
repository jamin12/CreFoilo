package emyo.jamin.jej.crefoilo.service;

import java.util.List;

import emyo.jamin.jej.crefoilo.dto.PortfolioDto;

public interface PortfolioListService {
    List<PortfolioDto> findPortfolioList(String userId);

    void deletePortfolio(Long portfolioId, String userId);
}
