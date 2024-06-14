package com.bitstudy.board.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@ToString
//@EntityListeners(AuditingEntityListener.class)
//@Entity
@Table(indexes = {
        @Index(columnList = "content"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy"),
})
public class Ex02_2_ArticleComment extends Ex02_3_AuditingFields{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;//고유번호

  @Setter
  @ManyToOne(optional = false)
  private Ex02_1_Article article;//연관관계 매핑.

  @Setter
  @Column(nullable = false, length = 500)
  private String content;//본문


  //메타데이터-extends Ex02_3_AuditingFields로 메타데이터 상속받음.

  protected Ex02_2_ArticleComment() {
  }

  private Ex02_2_ArticleComment(Ex02_1_Article article, String content) {
    this.content = content;
    this.article = article;
  }
  public static Ex02_2_ArticleComment of(Ex02_1_Article article, String content){
    return new Ex02_2_ArticleComment(article, content);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Ex02_2_ArticleComment that = (Ex02_2_ArticleComment) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
