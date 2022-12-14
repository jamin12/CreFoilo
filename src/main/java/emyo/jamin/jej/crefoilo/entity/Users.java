package emyo.jamin.jej.crefoilo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class Users extends BaseTimeEntity {
    @Id
    @Column(name = "user_id", length = 255)
    private String id;

    @Column(nullable = false, name = "user_email")
    private String userEmail;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private Role role;

    public Users update(String userEmail) {
        this.userEmail = userEmail;
        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}