package emyo.jamin.jej.crefoilo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import emyo.jamin.jej.crefoilo.dto.HomeViewDto;
import emyo.jamin.jej.crefoilo.dto.PortfolioDto;
import emyo.jamin.jej.crefoilo.entity.Portfolio;
import emyo.jamin.jej.crefoilo.repository.PortfolioRepository;
import emyo.jamin.jej.crefoilo.utils.Validation;

/**
 * @author 강민진
 */
@Service
public class PortfolioServiceImpl implements PortfolioService {

    @Autowired
    private PortfolioRepository portfolioRepository;

    @Autowired
    private Validation validation;

    /**
     * 포트폴리오 리스트 조회
     * 
     * @param userId 유저 아이디
     * @return List<porfolioDto> 반환
     */
    @Override
    public List<PortfolioDto> findPortfolioList(String userId) {
        List<Portfolio> portfolios = portfolioRepository.findByUserId(userId);
        List<PortfolioDto> portfoliolist = new ArrayList<>();

        for (Portfolio portfolio : portfolios) {
            PortfolioDto portfolioDto = new PortfolioDto();
            portfolioDto.setPortfolioName(portfolio.getPortfolioName());
            portfolioDto.setCreatedDate(portfolio.getCreatedDate());
            portfolioDto.setUpdatedDate(portfolio.getModifiedDate());
            portfolioDto.setPortfolioId(portfolio.getPortfolioId());

            portfoliolist.add(portfolioDto);
        }

        return portfoliolist;
    }

    @Transactional
    @Override
    public void deletePortfolio(Long portfolioId, String userId) {
        validation.checkUserHasPortfolio(portfolioId, userId);
        portfolioRepository.delete(portfolioRepository.findById(portfolioId).get());
    }

    /**
     * home 화면 조회
     * 
     * @param portfolioId
     */
    @Override
    public HomeViewDto findPortfolioHome(Long portfolioId) {
        return new HomeViewDto(portfolioRepository.findHomeByPortfolioId(portfolioId));
    }

}
