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
    public List<SnsInfo> findByEmail(String email) {
        QSnsInfo qsnsInfo = QSnsInfo.snsInfo;
        return jpaQueryFactory.select(qsnsInfo).from(qsnsInfo).where(qsnsInfo.snsEmail.eq(email)).fetch();

    }

    /**
     * userID 로 유저 이름 찾기
     * 
     * @param userId
     */
    @Override
    public SnsInfo findByUserId(String userId) {
        QSnsInfo qsnsInfo = QSnsInfo.snsInfo;
        return jpaQueryFactory.selectFrom(qsnsInfo).where(qsnsInfo.id.eq(userId)).fetchOne();
    }

}
