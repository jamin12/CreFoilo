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
public class PortfolioListImpl implements PortfolioList {

    @Autowired
    private PortfolioRepository portfolioRepository;

    /**
    * createPortfolio 
    * @return 
    */
    public void createPortfolio(){

    }

    /**
    * updatePortfolio 
    * @return 
    */
    public void updatePortfolio(){

    }

    /**
     * deletePortfolio
     * @return 
     */
    public void deletePortfolio(){

    }

    /**
     * findPortfolioList
     * @param portfolio_id 포트폴리오아이디
     * @return 
     */

    public void findPortfolioList() {
        List<Portfolio> portfolio = portfolioRepository.ByUserId();
        List<PortfolioDto> portfoliolist = new ArrayList<>();

        for (Portfolio portfolio1 : portfolio){
            PortfolioDto portfolioDto = new PortfolioDto();
            portfolioDto.setPortfolioName(portfolio1.getPortfolioName());
            portfoliolist.add(portfolioDto);
        }

        System.out.println();
    }
        
        
    }

