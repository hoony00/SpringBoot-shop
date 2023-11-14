package inhatc.spring.shop.common;

import inhatc.spring.shop.Config.AuditorAwareImpl;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;


@EntityListeners(value = {AuditorAwareImpl.class}) // AuditorAwareImpl.class를 통해 생성, 수정 시간을 자동으로 업데이트
@MappedSuperclass // 해당 클래스를 상속받는 클래스에게 속성만 제공
@Getter
@Setter
public abstract class BaseTimeEntity {

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime regTime;


    @LastModifiedDate
    private LocalDateTime updateTime;


}
