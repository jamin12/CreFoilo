package emyo.jamin.jej.crefoilo.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.querydsl.core.Tuple;

import emyo.jamin.jej.crefoilo.entity.Portfolio;
import emyo.jamin.jej.crefoilo.entity.QPortfolio;
import emyo.jamin.jej.crefoilo.entity.QProject;
import emyo.jamin.jej.crefoilo.repository.PortfolioRepository;
import emyo.jamin.jej.crefoilo.repository.ProejectRepository;

/**
 * @author 강경민
 */
@Component
public class Validation {
    @Autowired
    private PortfolioRepository portfolioRepository;

    @Autowired
    private ProejectRepository proejctRepository;

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

    /**
     * 유저가 프로젝트를 가지고 있는지 체크
     * 
     * @param portfolioId
     * @param userId
     */
    public void checkUserHasProject(Long projectId, String userId) {
        QPortfolio qPortfolio = QPortfolio.portfolio;
        Tuple findedProject = proejctRepository.findPortfolioByProjectId(projectId, userId);
        if (findedProject == null || !findedProject.get(qPortfolio).getUserId().equals(userId)) {
            throw new CustomException(ErrorCode.PROJECT_NOT_FOUND);
        }

    }
}
