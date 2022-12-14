package emyo.jamin.jej.crefoilo.repository.dslmodel;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.querydsl.jpa.impl.JPAQueryFactory;

import emyo.jamin.jej.crefoilo.entity.DocumentUrl;
import emyo.jamin.jej.crefoilo.entity.QDocumentUrl;

public class ProjectDocumentUrlRepositoryDslImpl implements ProjectDocumentUrlRepositoryDsl {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    public ProjectDocumentUrlRepositoryDslImpl(EntityManager em) {
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<DocumentUrl> findbyProjectId(Long ProjectId) {
        QDocumentUrl qoDocumentUrl = QDocumentUrl.documentUrl1;
        return jpaQueryFactory.selectFrom(qoDocumentUrl)
                .where(qoDocumentUrl.projectId.eq(ProjectId))
                .fetch();
    }

}
