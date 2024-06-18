package com.bitstudy.board.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@ToString(callSuper = true)
@Table(indexes = {
        @Index(columnList = "userId"),
        @Index(columnList = "email"),
        @Index(columnList = "nickname"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@Entity
public class UserAccount extends Ex02_3_AuditingFields {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Setter
  @Column(nullable = false)
  private String userId;
  @Setter
  @Column(nullable = false)
  private String userPassword;
  @Setter
  private String email;
  @Setter
  @Column(nullable = false)
  private String nickname;
  @Setter
  @Column(nullable = false)
  private String memo;

  /* 그외 네 개는 메타데이터*/

  protected UserAccount(){}

  private UserAccount(String userId, String userPassword, String email, String nickname, String memo) {
    this.userId = userId;
    this.userPassword = userPassword;
    this.email = email;
    this.nickname = nickname;
    this.memo = memo;
  }

  public static UserAccount of(String userId, String userPassword, String email, String nickname, String memo){
    return new UserAccount(userId, userPassword, email, nickname, memo);
  }
}
