package emyo.jamin.jej.crefoilo.repository.dslmodel;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.querydsl.jpa.impl.JPAQueryFactory;

import emyo.jamin.jej.crefoilo.entity.QSnsInfo;
import emyo.jamin.jej.crefoilo.entity.SnsInfo;

public class SnsInfoDslImpl implements SnsInfoDsl {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    public SnsInfoDslImpl(EntityManager em) {
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<SnsInfo> getUserByEmail(String email) {
        QSnsInfo qsnsInfo = QSnsInfo.snsInfo;
        return jpaQueryFactory.select(qsnsInfo).from(qsnsInfo).where(qsnsInfo.snsEmail.eq(email)).fetch();

    }

}
