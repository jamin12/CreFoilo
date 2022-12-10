package emyo.jamin.jej.crefoilo.service;

import java.util.List;

import emyo.jamin.jej.crefoilo.dto.HomeViewDto;
import emyo.jamin.jej.crefoilo.dto.PortfolioDto;
import emyo.jamin.jej.crefoilo.dto.PortfolioHomeDto;
import emyo.jamin.jej.crefoilo.entity.Portfolio;

public interface PortfolioService {
    List<PortfolioDto> findPortfolioList(String userId);

    void deletePortfolio(Long portfolioId, String userId);

    HomeViewDto findPortfolioHome(Long portfolioId);

    HomeViewDto findPortfolioHome(Long portfolioId, String userId);

    Portfolio CUPortfolioHome(PortfolioHomeDto portfolioHomeDto, String userId);
}
