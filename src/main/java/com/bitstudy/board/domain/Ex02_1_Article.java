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
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/** Article 과 ArticleComment 에 있는 공통 필드(메타데이터) 들이 있는데, 별도로 빼서 관리할 것.
 * 이유: Article과 ArticleComment 처럼 FK 키로 엮여있는 테이블들을 만드는 경우, 모든 domain 파일들에 있는 중복코드들이 들어가게 됨.(id, created_by, created_at, modified_by, modified_at)
 * 그래서 별도의 파일에 공통되는 것들을 다 몰아넣고 사용하게 만들어볼 것이다.
 *
 * (공통 필드는 회사마다 다 다르다.)
 *
 * 1엔티티 = 1테이블 원칙을 선호하는 곳도 있고,
 * 지금처럼 간결하게 코드 구성을 선호하는 곳도 있음.
 *
 * =추출하는 방법=
 * 1) Embedded - 공통되는 필드들(id, created_by, created_at, modified_by, modified_at)을 하나의 클래스로 만들어서 @Embedded 어노테이션이 있는 곳에서 지원하는 방식. (고전적? 인 방법)
 *    ex) Class Tmp {
 *      id, created_by, created_at, modified_by, modified_at 다 넣고
 *    }
 * @ Embedded Tmp t; <- 이렇게 하면 이 코드가 있는 자리에 Tmp 클래스 안에 있는 것들이 들어온다.
 *
 * 2) @MappedSuperClass - 요즘 방식(요즘 실무에서는 이걸 쓴다.)
 * @ MappedSuperClass 어노테이션이 붙은
 * 1. domain > AuditingFields 파일 생성하기
 * 2. 공통필드 옮겨가기.(메타데이터)
 * */


@Getter // Getter 자동생성
@ToString//오버라이드 toString
//@EntityListeners(AuditingEntityListener.class)
/* auditing 관련된 거. Ex02_3으로 이동해야 함.*/
//@Entity
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "hashtag"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy"),
        /* AuditingFields.java 사용시 @Index 어노테이션 부분도 보내야 하는데 그렇게 하지 않는다. 못 보내는 건 아닌데, 그렇게 보내려면 추가적인 세팅이 많이 필요함. (비효율적.) */
})
public class Ex02_1_Article extends Ex02_3_AuditingFields{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // = auto_increment

  private Long id;//고유번호


  @Setter
  @Column(nullable = false)
  private String title;//제목

  @Setter
  @Column(nullable = false, length = 10000) // =not null varchar(10000)
  private String content;//본문

  @Setter
  private String hashtag;//해시태그


  @OrderBy("id")
  @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
  @ToString.Exclude
  private final Set<Ex02_2_ArticleComment> articleComment = new LinkedHashSet<>();

  //메타데이터
//  @CreatedDate
//  @Column(nullable = false) private LocalDateTime createdAt;//생성일자
//  @CreatedBy
//  @Column(nullable = false, length = 100) private String createdBy;//생성자
//  @LastModifiedDate
//  @Column(nullable = false) private LocalDateTime modifiedAt;//수정일자
//  @LastModifiedBy
//  @Column(nullable = false, length = 100) private String modifiedBy;//수정자


  protected Ex02_1_Article() {
  }

  /* 사용자가 입력하는 값만 받는 생성자를 생성한다. 나머지 메타데이터들은 시스템이 알아서 하게 해줘야 함.*/
  private Ex02_1_Article(String title, String content, String hashtag) {
    this.title = title;
    this.content = content;
    this.hashtag = hashtag;
  }

  public static Ex02_1_Article of(String title, String content, String hashtag){
    return new Ex02_1_Article(title, content, hashtag);
  }

  @Override
  public boolean equals(Object o) { // 동등성 비교. 주소값.
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Ex02_1_Article that = (Ex02_1_Article) o;
//    return Objects.equals(id, that.id);
    return id!=null && id.equals(that.id);
  }

  @Override
  public int hashCode() {//동일성 비교. 주소값 대신 데이터값을 16진수로 바꾼 후 그 숫자를 비교하는 것이다.
    return Objects.hashCode(id);
  }

}