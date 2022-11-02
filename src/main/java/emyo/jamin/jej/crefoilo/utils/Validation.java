package emyo.jamin.jej.crefoilo.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import emyo.jamin.jej.crefoilo.entity.Portfolio;
import emyo.jamin.jej.crefoilo.repository.PortfolioRepository;

/**
 * @author 강경민
 */
@Component
public class Validation {
    @Autowired
    private PortfolioRepository portfolioRepository;

    /**
     * 유저가 포트폴리오를 가지고 있는지 체크
     * 
     * @param portfolioId
     * @param userId
     */
    public void checkUserHasPortfolio(Long portfolioId, String userId) {
        List<Portfolio> findedPortfolios = portfolioRepository.findByUserId(userId);
        boolean checkFlag = false;
        for (Portfolio portfolio : findedPortfolios) {
            if (portfolio.getPortfolioId() == portfolioId) {
                checkFlag = true;
            }
        }
        if (!checkFlag) {
            throw new CustomException(ErrorCode.POST_NOT_FOUND);
        }
    }
}
