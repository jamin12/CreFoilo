package emyo.jamin.jej.crefoilo.repository.dslmodel;

import java.util.List;

import javax.persistence.EntityManager;

import com.querydsl.jpa.impl.JPAQueryFactory;

import emyo.jamin.jej.crefoilo.entity.Contact;
import emyo.jamin.jej.crefoilo.entity.QContact;

/**
 * @author 강경민
 */
public class ContactDslImpl implements ContactDsl {
    private JPAQueryFactory jpaQueryFactory;

    public ContactDslImpl(EntityManager em) {
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    /**
     * 포트폴리오 아이디로 contact조회
     */
    @Override
    public List<Contact> findByPortfolioId(Long portfolioId) {
        QContact qContact = QContact.contact;
        return jpaQueryFactory.selectFrom(qContact)
                .where(qContact.portfolioId.eq(portfolioId))
                .fetch();
    }
}
