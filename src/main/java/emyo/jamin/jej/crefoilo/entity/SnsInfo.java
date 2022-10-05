package emyo.jamin.jej.crefoilo.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
public class SnsInfo extends BaseTimeEntity {
    @Id
    @Column(name = "sns_info_id")
    private Long id;

    @Column(name = "sns_type", length = 45)
    private String snsType;

    @Column(name = "sns_name", length = 45)
    private String snsName;

    @Column(name = "sns_img", length = 45)
    private String snsImg;

    @Column(name = "sns_email", length = 45)
    private String snsEmail;

    public SnsInfo update(String snsEmail, String snsName, String snsImg) {
        this.snsEmail = snsEmail;
        this.snsName = snsName;
        this.snsImg = snsImg;
        return this;
    }
}
