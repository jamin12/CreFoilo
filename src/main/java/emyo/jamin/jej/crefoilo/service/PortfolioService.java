package emyo.jamin.jej.crefoilo.service;

import emyo.jamin.jej.crefoilo.dto.HomeViewDto;

public interface PortfolioService {
    void findPortfolioList();

    HomeViewDto findPortfolioHome(Long portfolioId);
}
