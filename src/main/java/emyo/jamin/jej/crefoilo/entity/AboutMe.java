package emyo.jamin.jej.crefoilo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "about_me")
public class AboutMe extends BaseTimeEntity {

    @Id
    @Column(name = "about_me_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long aboutMe_id; // '나의 대해 ID'

    @Column(name = "portfolio_id")
    private Long portfolioId; // '포트폴리오 ID'

    @Column(name = "about_me_bg_color", length = 124, nullable = false)
    private String aboutMeBgColor; // '나에 대해 배경 Color'

    @Column(name = "about_me_type", nullable = false)
    private Integer aboutMeType; // '나에 대해 type'

    @Column(name = "about_me_title1", length = 255, nullable = true)
    private String aboutMeTitle1; // '제목1'

    @Column(name = "about_me_title2", length = 255, nullable = true)
    private String aboutMeTitle2; // '제목2'

    @Column(name = "about_me_title3", length = 255, nullable = true)
    private String aboutMeTitle3; // '제목3'

    @Column(name = "about_me_intro1", columnDefinition = "TEXT", nullable = true)
    private String aboutMeIntro1; // '소개1'

    @Column(name = "about_me_intro2", columnDefinition = "TEXT", nullable = true)
    private String aboutMeIntro2; // '소개2'

    @Column(name = "about_me_intro_color", length = 124, nullable = false)
    private String aboutMeIntroColor; // '소개 Color'

    @Column(name = "about_me_title_color", length = 124, nullable = false)
    private String aboutMeTitleColor; // '제목 Color'

}
