package com.bitstudy.board.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@ToString
@Entity
@Table(indexes = {
        @Index(columnList = "content"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy"),
})
public class ArticleComment extends Ex02_3_AuditingFields{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Setter
  @ManyToOne(optional = false)
  private Article article;

  /* 추가 생성 - 유저정보 */
  @Setter
  @ManyToOne(optional = false)
//  @JoinColumn(name = "userId")
  private UserAccount userAccount;

  @Setter
  @Column(nullable = false, length = 500)
  private String content;

  protected ArticleComment() {
  }

  private ArticleComment(Article article, UserAccount userAccount, String content) {
    this.content = content;
    this.userAccount = userAccount;
    this.article = article;
  }
  public static ArticleComment of(Article article, UserAccount userAccount, String content){
    return new ArticleComment(article,userAccount, content);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ArticleComment that = (ArticleComment) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
