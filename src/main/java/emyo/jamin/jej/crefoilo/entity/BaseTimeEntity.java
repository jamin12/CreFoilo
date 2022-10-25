package emyo.jamin.jej.crefoilo.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;

@Getter
@MappedSuperclass
public class BaseTimeEntity {

    @CreationTimestamp
    @Column(name = "created_at", nullable = true)
    private LocalDateTime createdDate;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = true)
    private LocalDateTime modifiedDate;

}
