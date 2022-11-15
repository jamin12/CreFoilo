package emyo.jamin.jej.crefoilo.dto;

import emyo.jamin.jej.crefoilo.entity.AboutMe;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AboutmeDto {
    private Long aboutMeId; // '나의 대해 ID'
    private Long portfolioId; // '포트폴리오 ID'
    private String aboutMeBgColor; // '나에 대해 배경 Color'
    private Integer aboutMeType; // '나에 대해 type'
    private String aboutMeTitle1; // '제목1'
    private String aboutMeTitle2; // '제목2'
    private String aboutMeTitle3; // '제목3'
    private String aboutMeIntro1; // '소개1'
    private String aboutMeIntro2; // '소개2'
    private String aboutMeImg; // '소개2'

    public AboutmeDto(AboutMe aboutMe) {
        this.aboutMeId = aboutMe.getAboutMeId();
        this.portfolioId = aboutMe.getPortfolioId();
        this.aboutMeBgColor = aboutMe.getAboutMeBgColor();
        this.aboutMeType = aboutMe.getAboutMeType();
        this.aboutMeTitle1 = aboutMe.getAboutMeTitle1();
        this.aboutMeIntro1 = aboutMe.getAboutMeIntro1();
        this.aboutMeImg = aboutMe.getAboutMeImg();
        if (aboutMe.getAboutMeType() == 1) {
            this.aboutMeTitle2 = aboutMe.getAboutMeTitle2();
            this.aboutMeTitle3 = aboutMe.getAboutMeTitle3();
            this.aboutMeIntro2 = aboutMe.getAboutMeIntro2();
        }
    }
}
