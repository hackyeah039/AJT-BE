package com.ajt.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;


/**
 * 최초 작성일 : 2021-12-09
 * 최초 작성자 : Jang
 *
 * 데이터 베이스의 공통으로 사용되는 생성일과 마지막 업데이트 시간 정보를 가지고있는 클래스
 * Entity 클래스가 상속 받아 사용한다.
 */

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class TimeEntity {

    // 생성한 날짜, 시간
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime CreatedAt;

    // 마지막 수정한 날짜, 시간
    @LastModifiedDate
    private LocalDateTime updatedAt;

}
