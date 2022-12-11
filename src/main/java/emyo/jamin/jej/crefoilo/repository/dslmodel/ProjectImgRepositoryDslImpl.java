package emyo.jamin.jej.crefoilo.repository.dslmodel;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.querydsl.jpa.impl.JPAQueryFactory;

import emyo.jamin.jej.crefoilo.entity.ProjectImg;
import emyo.jamin.jej.crefoilo.entity.QProjectImg;

public class ProjectImgRepositoryDslImpl implements ProjectImgRepositoryDsl {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    public ProjectImgRepositoryDslImpl(EntityManager em) {
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    /**
     * 프로젝트 이미지 리스트 가져오기
     * 
     * @param projectId
     */
    @Override
    public List<ProjectImg> findbyProjectId(Long projectId) {
        QProjectImg qProjectImg = QProjectImg.projectImg;
        return jpaQueryFactory.selectFrom(qProjectImg)
                .where(qProjectImg.projectId.eq(projectId))
                .fetch();

    }
}
