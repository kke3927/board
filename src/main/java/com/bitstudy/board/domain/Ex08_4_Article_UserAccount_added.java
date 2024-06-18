package com.bitstudy.board.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

//@Getter
//@ToString
//@Entity
//@Table(indexes = {
//        @Index(columnList = "title"),
//        @Index(columnList = "hashtag"),
//        @Index(columnList = "createdAt"),
//        @Index(columnList = "createdBy"),
//})
public class Ex08_4_Article_UserAccount_added extends Ex02_3_AuditingFields{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // = auto_increment
  private Long id;

  /* 추가 생성 - 유저정보 */
  @Setter
  @ManyToOne(optional = false)
  @JoinColumn(name = "userId")
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

  protected Ex08_4_Article_UserAccount_added() {
  }

  private Ex08_4_Article_UserAccount_added(String title, String content, String hashtag) {
    this.title = title;
    this.content = content;
    this.hashtag = hashtag;
  }

  public static Ex08_4_Article_UserAccount_added of(String title, String content, String hashtag) {
    return new Ex08_4_Article_UserAccount_added(title, content, hashtag);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Ex08_4_Article_UserAccount_added article = (Ex08_4_Article_UserAccount_added) o;
    return Objects.equals(id, article.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
