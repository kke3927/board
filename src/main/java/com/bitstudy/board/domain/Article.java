package com.bitstudy.board.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@ToString
@Entity
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "hashtag"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy"),
})
public class Article extends Ex02_3_AuditingFields{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // = auto_increment
  private Long id;

  /* 추가 생성 - 유저정보 */
  @Setter
  @ManyToOne(optional = false) 
//  @JoinColumn(name = "userId")
  private UserAccount userAccount;


  @Setter
  @Column(nullable = false)
  private String title;//제목

  @Setter
  @Column(nullable = false, length = 10000) // =not null varchar(10000)
  private String content;//본문

  @Setter
  private String hashtag;//해시태그

/* 양방향 바인딩 */
  @OrderBy("id")
  @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
  @ToString.Exclude
  private final Set<ArticleComment> articleComment = new LinkedHashSet<>();

  protected Article() {
  }

  private Article(UserAccount userAccount,String title, String content, String hashtag) {
    this.userAccount = userAccount;
    this.title = title;
    this.content = content;
    this.hashtag = hashtag;
  }

  public static Article of(UserAccount userAccount,String title, String content, String hashtag) {
    return new Article(userAccount, title, content, hashtag);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Article article = (Article) o;
    return Objects.equals(id, article.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
