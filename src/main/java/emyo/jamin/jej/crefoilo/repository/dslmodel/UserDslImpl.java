package emyo.jamin.jej.crefoilo.repository.dslmodel;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.querydsl.jpa.impl.JPAQueryFactory;

import emyo.jamin.jej.crefoilo.entity.QUser;
import emyo.jamin.jej.crefoilo.entity.User;

public class UserDslImpl implements UserDsl {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    public UserDslImpl(EntityManager em) {
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<User> getUserByEmail(String email) {
        QUser quser = QUser.user;

        return jpaQueryFactory.select(quser).from(quser).where(quser.email.eq(email)).fetch();
    }

}
