package emyo.jamin.jej.crefoilo.repository.dslmodel;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.querydsl.jpa.impl.JPAQueryFactory;

import emyo.jamin.jej.crefoilo.entity.QSnsInfo;
import emyo.jamin.jej.crefoilo.entity.QUsers;
import emyo.jamin.jej.crefoilo.entity.Users;

public class UserDslImpl implements UserDsl {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    public UserDslImpl(EntityManager em) {
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Users> findByEmail(String email) {
        QUsers quser = QUsers.users;
        return jpaQueryFactory.select(quser).from(quser).where(quser.userEmail.eq(email)).fetch();
    }
}
