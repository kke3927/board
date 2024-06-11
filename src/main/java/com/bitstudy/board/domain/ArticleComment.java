package com.bitstudy.board.domain;

import java.time.LocalDateTime;

public class ArticleComment {
  private Long id;//고유번호
  private Article article;//연관관계 매핑.
  /**
   * 연관관계 없이 코드를 짠다면
   * private Long FK_id 이런 식으로 그냥 하면 됨.
   * private Article article; 은
   * Article과 관계를 맺고 있는 필드라는 것을 객체지향적으로 표현한 것이다. */
  private String content;//본문

  private LocalDateTime createdAt;//생성일자
  private String createdBy;//생성자
  private LocalDateTime modifiedAt;//수정일자
  private String modifiedBy;//수정자
}
