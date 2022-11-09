package emyo.jamin.jej.crefoilo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import emyo.jamin.jej.crefoilo.dto.PortfolioDto;
import emyo.jamin.jej.crefoilo.entity.Portfolio;
import emyo.jamin.jej.crefoilo.repository.dslmodel.PortfolioDsl;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Long>, PortfolioDsl {

}
