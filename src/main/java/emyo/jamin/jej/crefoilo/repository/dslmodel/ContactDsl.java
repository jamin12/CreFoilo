package emyo.jamin.jej.crefoilo.repository.dslmodel;

import java.util.List;

import emyo.jamin.jej.crefoilo.entity.Contact;

public interface ContactDsl {
    List<Contact> findByPortfolioId(Long portfolioId);
}
