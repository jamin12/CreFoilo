package emyo.jamin.jej.crefoilo.repository.dslmodel;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.querydsl.jpa.impl.JPAQueryFactory;

import emyo.jamin.jej.crefoilo.entity.LangSkill;
import emyo.jamin.jej.crefoilo.entity.QLangSkill;
import emyo.jamin.jej.crefoilo.entity.QLanguage;

/**
 * @author 강경민
 */
public class LanguageSkillDslImpl implements LanguageSkillDsl {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    public LanguageSkillDslImpl(EntityManager em) {
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    /**
     * language 아이디로 lang skill 조회
     * 
     * @param langId
     * @return
     */
    @Override
    public List<LangSkill> findByLangId(Long langId) {
        QLanguage qLang = QLanguage.language;
        QLangSkill qLangSkill = QLangSkill.langSkill;
        return jpaQueryFactory.selectFrom(qLangSkill)
                .join(qLang).on(qLangSkill.languageId.eq(qLang.languageId))
                .where(qLangSkill.languageId.eq(langId))
                .fetch();
    }
}
