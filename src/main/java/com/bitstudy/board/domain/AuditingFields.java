package com.bitstudy.board.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/* 클래스 import : ctrl + alt + O */
@MappedSuperclass /* 부모 클래스는 테이블과 매핑하지 않고, 부모 클래스를 상속받는 자식 클래스에게 부모 클래스가 가지는 컬럼만 매핑 정보로 제공하고 싶을 때 사용. */
@EntityListeners(AuditingEntityListener.class)
@Getter // 필드에 접근하기 위함.
@ToString // 출력 위해서.
public abstract class AuditingFields {


  @CreatedDate
  @Column(nullable = false) private LocalDateTime createdAt;//생성일자
  @CreatedBy
  @Column(nullable = false, length = 100) private String createdBy;//생성자
  @LastModifiedDate
  @Column(nullable = false) private LocalDateTime modifiedAt;//수정일자
  @LastModifiedBy
  @Column(nullable = false, length = 100) private String modifiedBy;//수정자
}
