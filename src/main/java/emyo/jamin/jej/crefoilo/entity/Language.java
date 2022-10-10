package emyo.jamin.jej.crefoilo.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Table(name = "language")
public class Language extends BaseTimeEntity {

    @Id
    @Column(name = "language_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long languageId; // '언어 ID',

    @Column(name = "portfolio_id", nullable = false)
    private Long portfolioId; // '포트폴리오 ID',

    @Column(name = "lang_name", length = 45, nullable = true)
    private String langName; // '언어 이름',

    @Column(name = "lang_detail", columnDefinition = "TEXT", nullable = true)
    private String langDetail; // '언어 배운점 설명',

    @Column(name = "lang_frequency", nullable = true)
    private Integer langFrequency; // '언어 배운점 설명',
}
