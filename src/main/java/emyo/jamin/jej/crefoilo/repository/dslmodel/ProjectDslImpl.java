package emyo.jamin.jej.crefoilo.repository.dslmodel;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.querydsl.jpa.impl.JPAQueryFactory;

import emyo.jamin.jej.crefoilo.entity.Project;
import emyo.jamin.jej.crefoilo.entity.QDocumentUrl;
import emyo.jamin.jej.crefoilo.entity.QProject;
import emyo.jamin.jej.crefoilo.entity.QProjectImg;
import emyo.jamin.jej.crefoilo.entity.QTechnicalStack;

public class ProjectDslImpl implements ProjectDsl {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    public ProjectDslImpl(EntityManager em) {
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    /**
     * 프로젝트 리스트 조회
     * 
     * @param portfolioId
     */
    @Override
    public List<Project> findByportfolioId(Long portfolioId) {
        QProject qProject = QProject.project;
        return jpaQueryFactory.selectFrom(qProject)
                .where(qProject.portfolioId.eq(portfolioId))
                .fetch();
    }

    /**
     * 프로젝트 상세 정보 조회
     * 
     * @param projectId
     */
    @Override
    public String findByprojectId(Long projectId) {
        QProject qProject = QProject.project;
        QTechnicalStack qTechnicalStack = QTechnicalStack.technicalStack;
        QDocumentUrl qDocumentUrl = QDocumentUrl.documentUrl1;
        QProjectImg qProjectImg = QProjectImg.projectImg;

        return null;
    }

}
