package com.bitstudy.board.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@ToString
@Entity
@Table(indexes = {
        @Index(columnList = "content"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy"),
})
public class Ex01_2_ArticleComment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;//고유번호
  
  @Setter
  @ManyToOne(optional = false)
  private Ex01_1_Article article;//연관관계 매핑.
  /*
  * 객체지향적 표현.
  * 이를 위해 Article과의 연관관계(현재 파일 기준)를 명시해 줘야 함. (@ManyToOne) (다대일)
  * 필수값이라는 뜻으로 optional = false 를 걸어줘야 함.
  * 댓글은 여러개 : 게시글 1개의 관계이기 때문에 여기에서만 걸면 단방향 바인딩이 됨.
  * Article에서도 바인딩을 해서 양방향 바인딩으로 만들어줘야 함.
  * */
  
  @Setter
  @Column(nullable = false, length = 500)
  private String content;//본문


  //메타데이터
  @CreatedDate
  @Column(nullable = false) private LocalDateTime createdAt;//생성일자
  @CreatedBy
  @Column(nullable = false, length = 100) private String createdBy;//생성자
  @LastModifiedDate
  @Column(nullable = false) private LocalDateTime modifiedAt;//수정일자
  @LastModifiedBy
  @Column(nullable = false, length = 100) private String modifiedBy;//수정자

  protected Ex01_2_ArticleComment() {
  }

  private Ex01_2_ArticleComment(Ex01_1_Article article, String content) {
    this.content = content;
    this.article = article;
  }
  public static Ex01_2_ArticleComment of(Ex01_1_Article article, String content){
    return new Ex01_2_ArticleComment(article, content);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Ex01_2_ArticleComment that = (Ex01_2_ArticleComment) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

  /* Ex01_1, Ex01_2, Ex01_3 하고? 테스트?
  *
  * 방법: 
  * 1) 서비스탭 열고(Alt+8)
  * 2) 좌측 상단 + 버튼누르기
  * 3) run configuration 선택
  * spring boot 에서 실행하기
  * 이유: run 탭에서 빌드작업을 하거나 테스트를 하게 되면 실행 로그와 서비스로그를 분리해서 볼 수 있음.*/
}
