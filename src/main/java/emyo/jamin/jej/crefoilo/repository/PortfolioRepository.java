package emyo.jamin.jej.crefoilo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import emyo.jamin.jej.crefoilo.entity.Portfolio;
import emyo.jamin.jej.crefoilo.repository.dslmodel.PortfolioDsl;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long>, PortfolioDsl {

}
