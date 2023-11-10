package inhatc.spring.shop.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
public abstract class Base {

    //등록일
    @CreatedDate
    @Column(updatable = false)
    protected LocalDateTime createDate;

    //수정일
    @LastModifiedDate
    protected LocalDateTime lastModifedDate;

    //등록자
    @CreatedBy
    @Column(updatable = false)
    protected Long createBy;

    //수정자
    @LastModifiedBy
    protected Long lastModifedBy;

}
