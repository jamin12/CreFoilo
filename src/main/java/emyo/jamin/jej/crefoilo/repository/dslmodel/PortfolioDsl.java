package emyo.jamin.jej.crefoilo.repository.dslmodel;

import java.util.List;

import emyo.jamin.jej.crefoilo.entity.Portfolio;

public interface PortfolioDsl {
    List<Portfolio> findByUserId(String userId);

    Portfolio findHomeByPortfolioId(Long portfolioId);
}
