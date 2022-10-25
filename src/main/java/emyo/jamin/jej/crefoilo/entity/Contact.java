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
@Table(name = "contact")
public class Contact extends BaseTimeEntity {

    @Id
    @Column(name = "contact_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long contactId; // '내 컨택트 정보 ID',

    @Column(name = "portfolio_id", nullable = false)
    private Long portfolioId; // '포트폴리오 ID',

    @Column(name = "contact_img_url", nullable = true, length = 255)
    private String contactImgUrl; // '로고 url',

    @Column(name = "contact_info", nullable = true, length = 255)
    private String contactInfo; // '정보',
}
