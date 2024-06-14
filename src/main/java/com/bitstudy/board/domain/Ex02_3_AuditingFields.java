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
/** Article 과 ArticleComment 의 중복 필드들을 하나로 합치기.
 * 1) 각 파일들에서 중복됐던 부분들을 그대로 가져오기.
 *    - 가져오면서 @MappedSuperclass 를 클래스에 주기
 * 2) auditing 과 관련된 거 다 가져오기.
 *    - Article 클래스에서 auditing 하는 게 다 이동했으므로 auditing 에 관련된 부분 다 가져오기.
 * 3) @Getter, @ToString달기(메타데이터라 setter는 필요없었음.)
 * 4) Article 클래스에 가서 extends 달아주기(extends AuditingFields 클래스 상속받게 함)
 *
 *    */


/* 클래스 import : ctrl + alt + O */
@MappedSuperclass /* 부모 클래스는 테이블과 매핑하지 않고, 부모 클래스를 상속받는 자식 클래스에게 부모 클래스가 가지는 컬럼만 매핑 정보로 제공하고 싶을 때 사용. */
@EntityListeners(AuditingEntityListener.class)
@Getter // 필드에 접근하기 위함.
@ToString // 출력 위해서.
public abstract class Ex02_3_AuditingFields {


  @CreatedDate
  @Column(nullable = false) private LocalDateTime createdAt;//생성일자
  @CreatedBy
  @Column(nullable = false, length = 100) private String createdBy;//생성자
  @LastModifiedDate
  @Column(nullable = false) private LocalDateTime modifiedAt;//수정일자
  @LastModifiedBy
  @Column(nullable = false, length = 100) private String modifiedBy;//수정자
}
