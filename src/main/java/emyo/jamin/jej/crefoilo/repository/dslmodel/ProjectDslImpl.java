package emyo.jamin.jej.crefoilo.repository.dslmodel;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import emyo.jamin.jej.crefoilo.entity.Project;
import emyo.jamin.jej.crefoilo.entity.QDocumentUrl;
import emyo.jamin.jej.crefoilo.entity.QPortfolio;
import emyo.jamin.jej.crefoilo.entity.QProject;
import emyo.jamin.jej.crefoilo.entity.QProjectImg;
import emyo.jamin.jej.crefoilo.entity.QTechnicalStack;

/**
 * @author 강경민
 */
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
    public List<Tuple> findByProjectId(Long projectId) {
        QProject qProject = QProject.project;
        QTechnicalStack qTechnicalStack = QTechnicalStack.technicalStack;
        QDocumentUrl qDocumentUrl = QDocumentUrl.documentUrl1;
        QProjectImg qProjectImg = QProjectImg.projectImg;
        return jpaQueryFactory
                .select(qProject, qTechnicalStack, qDocumentUrl, qProjectImg)
                .from(qProject)
                .join(qTechnicalStack).on(qProject.projectId.eq(qTechnicalStack.projectId))
                .join(qDocumentUrl).on(qProject.projectId.eq(qDocumentUrl.projectId))
                .join(qProjectImg).on(qProject.projectId.eq(qProjectImg.projectId))
                .where(qProject.projectId.eq(projectId))
                .fetch();
    }

    /**
     * 프로젝트 아이디로 포트폴리오 찾기
     * 
     * @param projectId
     */
    @Override
    public @Nullable Tuple findPortfolioByProjectId(Long projectId, String userId) {
        QProject qProject = QProject.project;
        QPortfolio qPortfolio = QPortfolio.portfolio;
        return jpaQueryFactory.select(qPortfolio, qProject)
                .from(qProject)
                .join(qPortfolio).on(qProject.portfolioId.eq(qPortfolio.portfolioId))
                .where(qProject.projectId.eq(projectId), qPortfolio.userId.eq(userId))
                .fetchOne();
    }

    /**
     * 프로젝트 아이디로 프로젝트 전부 찾기
     */
    @Override
    public List<Tuple> findProjectAllByPortfolioId(Long portfolioId) {

        // INNER JOIN technical_stack ts
        // on ts.project_id = p.project_id
        // INNER JOIN document_url du
        // on du.project_id = p.project_id
        // WHERE p.portfolio_id = 1;
        QProject qProject = QProject.project;
        QProjectImg qProjectImg = QProjectImg.projectImg;
        QTechnicalStack qTechnicalStack = QTechnicalStack.technicalStack;
        QDocumentUrl qDocumentUrl = QDocumentUrl.documentUrl1;
        return jpaQueryFactory.select(qProject, qProjectImg, qTechnicalStack, qDocumentUrl)
                .from(qProject)
                .join(qProjectImg).on(qProject.projectId.eq(qProjectImg.projectId))
                .join(qTechnicalStack).on(qProject.projectId.eq(qTechnicalStack.projectId))
                .join(qDocumentUrl).on(qProject.projectId.eq(qDocumentUrl.projectId))
                .where(qProject.portfolioId.eq(portfolioId))
                .fetch();
    }

}
