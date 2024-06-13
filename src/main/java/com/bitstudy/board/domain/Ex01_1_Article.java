package com.bitstudy.board.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/** 여기서는 gradle 방식으로 코드를 짜볼 것이다. + 엔티티로 등록하기.
 * JPA나 Lombok 같은 디펜던시들을 이용해서 코드를 짤 것이다.
 * (maven 때와 비슷한 것도 있고 다른 것들도 있어서 잘 기억해야 함.)
 *
 * JPA 애너테이션을 이용해서 이 클래스를 엔티티로 바꿀 것.
 *
 *  * JPA란?
 *  ==JPA==
 *    *varchar => 사이즈 명시하지 않으면 기본적으로 255 설정함
 *    JPA 진영의 ORM(Object Relational Mapping) 기술 표준
 *    Entity 를 분석하고, create나 insert 같은 sql 쿼리를 생성해주고 JDBC api 를 사용해서 DB 접근까지 해 준다. => 객체와 테이블을 맵핑해줌.
 *    
 *    Entity = 객체 = DB 테이블에 대응하는 하나의 클래스.
 * */
/*
* @Entity 등록할 때에는 반드시 필드 중에 어떤 게 PK(Primary key)인지 명시해줘야 함.
* @Id : 해당 필드가 PK라고 선언하는 애너테이션이다.
//@NoArgsConstructor//기본생성자
//@AllArgsConstructor//모든 매개변수 받는 생성자
*
* @Table : 엔티티와 매핑할 테이블을 지정하고, indexes 생략 시 매핑할 엔티티 이름을 테이블 이름으로 사용한다
* ex) @Index(name="원하는 명칭", columnList = "DB에서 사용할 컬럼명")
* 
* @Index: 데이터베이스 인덱스는 추가 쓰기 같은 저장공간을 미리 잡아놓는 어노테이션.
* 테이블에 대한 검색 속도를 향상시키는 데이터구조이다.
* @Entity와 세트로 사용해야 함.
* 이 기능은 최적화 작업의 효율을 높여준다.
*
* */
@Getter // Getter 자동생성
@ToString//오버라이드 toString
@Entity
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "hashtag"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy"),
})
public class Ex01_1_Article {
  /* private Long id 의 경우, auto_increment 처럼 DB에 값을 저장하고 그 이후에 키값을 구할 건데, 이걸 기본키 전략이라고 한다.*/
  @Id
  /* 기본키(PK)와 객체의 필드를 매핑시켜주는 어노테이션
  *@Id 만 사용할 경우: 키 직접 할당해야 함.
  *기본키를 직접 할당하지 않고 DB가 대신 생성해주는 값을 사용하려면
  */
  @GeneratedValue(strategy = GenerationType.IDENTITY) // = auto_increment
  //mysql auto_increment 는 IDENTITY 방식으로 만들어짐.

  /* @Setter도 @Getter처럼 클래스 단위로 걸 수 있지만, 그렇게 하면 모든 필드에 접근이 가능해진다.
  * 그런데 id나 메타데이터 같은 필드들은 다른 사람이 건드리지 않고 JPA에서 자동으로 세팅해주는 정보들이기 때문에 @Setter를 클래스 단위에 걸지 않고 별도로 필요한 필드들에만 @Setter를 달아주는 것을 추천한다.*/
  private Long id;//고유번호


  @Setter
  @Column(nullable = false) // 해당 컬럼이 not null인 경우 , @Column(nullable=false) 걸어줘야 함. 기본값은 true
  private String title;//제목

  @Setter
  @Column(nullable = false, length = 10000) // =not null varchar(10000)
  private String content;//본문

  @Setter
  private String hashtag;//해시태그



  // 양방향 바인딩 (Ex01_2의  // 연관관계 매핑 다 끝나고 와서 한다.)
  @OrderBy("id")//양방향 바인딩을 할 건데, 정렬 기준은 id로 하겠다는 뜻. desc는 없음.
  @OneToMany(mappedBy = "article", cascade = CascadeType.ALL) // mappedBy 로 양방향 바인딩의 이름을 지정함.
  @ToString.Exclude /* 매우중요 -
                        이게 없으면 Circle reference 이슈가 생김.
                        @ToString 이 모든 필드들을 다 찍고 Set<Ex01_2> 거를 찍으려고 하는데 그러면 ArticleComment 파일 가서도 거기에 있는 ToString 이 모든 원소들을 다 찍으려고 하면서 Article 이라는 걸 보는 순간 다시 Article 의 ToString 이 동작하면서 또 모든 원소들을 찍게 된다. 이런 식으로 서로가 서로를 계속 호출하면서 순환 참조를 하게 되면서 메모리가 터져서 시스템이 다운될 수 있다.

                        이를 해결하기 위해 ToString 을 끊어주려고 ToString.Exclude 를 사용한다.
                        (보통 부모에게 .exclude 를 달아준다.)
                        */
  private final Set<Ex01_2_ArticleComment> articleComment = new LinkedHashSet<>();

  /*
  * 메타데이터는 모든 엔티티마다 가지고 있을 데이터값임.
  * => JPA에서 자동으로 세팅할 수 있도록  jpa auditing 이라는 기능을 쓴다.
  * jpa auditing : JPA에서 자동으로 셋팅하게 해줄 때 사용하는 설정.
  *               이 기능을 위해서는 config 가 별도로 있어야 한다.
  *               (config 패키지 만들고 Ex01_3_JpaConfig 클래스 만들기)
  * */
  //메타데이터
  @CreatedDate
  @Column(nullable = false) private LocalDateTime createdAt;//생성일자
  @CreatedBy
  @Column(nullable = false, length = 100) private String createdBy;//생성자
  @LastModifiedDate
  @Column(nullable = false) private LocalDateTime modifiedAt;//수정일자
  @LastModifiedBy
  @Column(nullable = false, length = 100) private String modifiedBy;//수정자

  /* 이렇게 어노테이션만 붙여주기만 해도 auditing이 동작한다.
  *
  * @CreatedDate: 최초에 insert 할 때 자동으로 한 번 넣어준다.
  * @CreatedBy: 최초에 insert 할 때 자동으로 한 번 넣어준다.
  * @LastModifiedDate: 작성 당시의 시간을 매번 실시간으로 넣어준다.
  * @LastModifiedBy: 작성 당시의 이름을 매번 실시간으로 넣어준다.
  *
  * 위 4개 중에 생성일시나 수정일시는 알아낼 수 있는데... 최초의 생성자는 테스트시
  * 로그인을 하고 온 게 아니기 때문에 따로 알아낼 수가 없다.
  * 그래서 config 안에 있는 Jpaconfig의 public AuditorAware<String> auditorAware()를 사용한다.
  * 리턴타입이 String이기 때문에 createdBy 나 modifiedBy에 들어가질 수 있다.
  * */

  protected Ex01_1_Article() {
  }

  /* 사용자가 입력하는 값만 받는 생성자를 생성한다. 나머지 메타데이터들은 시스템이 알아서 하게 해줘야 함.*/
  private Ex01_1_Article(String title, String content, String hashtag) {
    this.title = title;
    this.content = content;
    this.hashtag = hashtag;
  }


  
  /* factory method pattern : 정적 팩토리 메서드
  * 정적 팩토리 메서드는 객체 생성의 역할을 하는 클래스 메서드(static 으로 무조건).
  * of 메서드를 이용해서, 직접적으로 생성자를 사용해서 객체를 생성한다.
  *
  * 기존 생성자 사용해도 되지만, 캡슐화를 통해 보안성.
  * ** 은닉화 다형성 추상화 **
  * 
  * 장점
  * 1) static 이기 때문에 new를 이용하지 않아도 생성자를 만들 수 있다.
  * 2) return 을 가지고 있기 때문에 상속을 사용할 때 값을 확인할 수 있다.
  * (한마디로 하위 자료형 객체를 반환할 수 있다.)
  * 3) 객체 생성을 캡슐화 가능.
  * */

  public static Ex01_1_Article of(String title, String content, String hashtag){
    return new Ex01_1_Article(title, content, hashtag);
  }

  @Override
  public boolean equals(Object o) { // 동등성 비교. 주소값.
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Ex01_1_Article that = (Ex01_1_Article) o;
//    return Objects.equals(id, that.id);
    return id!=null && id.equals(that.id);
  }

  @Override
  public int hashCode() {//동일성 비교. 주소값 대신 데이터값을 16진수로 바꾼 후 그 숫자를 비교하는 것이다.
    return Objects.hashCode(id);
  }

  /*
  * equals - 값이 같으면 true.
  *         둘 다 null 이어도 true 나온다.
  * hashCode - 객체를 식별하는 Integer 값.
  *           객체의 값을 특정 알고리즘을 이용해서 계산된 정수값을 지칭한다.
  *           장점: 객체를 비교할 때 드는 비용이 낮다.
  *           자바에서 두 개의 객체가 같은지 비교할 때 equals를 쓰는데
  *           여러 개의 객체를 비교할 때 equals 를 사용하게 되면 각각의 Integer
  *           값들을 비교할 때 많은 시간과 비용이 발생한다.
  * */
}