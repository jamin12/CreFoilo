package emyo.jamin.jej.crefoilo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import emyo.jamin.jej.crefoilo.dto.PortfolioDto;
import emyo.jamin.jej.crefoilo.entity.Portfolio;
import emyo.jamin.jej.crefoilo.repository.PortfolioRepository;

/**
 * @author 강민진
 */
@Service
public class PortfolioListServiceImpl implements PortfolioListService {

    @Autowired
    private PortfolioRepository portfolioRepository;

    /**
     * 포트폴리오 리스트 조회
     * 
     * @param portfolio_id 포트폴리오아이디
     * @return List<porfolioDto> 반환
     */
    public void findPortfolioList() {
        List<Portfolio> portfolios = portfolioRepository.findByUserId("152231594592157562165");
        List<PortfolioDto> portfoliolist = new ArrayList<>();

        for (Portfolio portfolio : portfolios) {
            PortfolioDto portfolioDto = new PortfolioDto();
            portfolioDto.setPortfolioName(portfolio.getPortfolioName());
            portfolioDto.setCreatedDate(portfolio.getCreatedDate());
            portfolioDto.setUpdatedDate(portfolio.getModifiedDate());
            portfolioDto.setPortfolioId(portfolio.getPortfolioId());

            portfoliolist.add(portfolioDto);
        }

        System.out.println(portfoliolist);
    }

}
