package com.bitstudy.board.repository;

import com.bitstudy.board.config.Ex01_3_JpaConfig;
import com.bitstudy.board.domain.Article;
import com.bitstudy.board.domain.Article;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest // 슬라이스 테스트를 할 수 있게 해주는 어노테이션.
             // 슬라이스 테스트를 할 때 우리가 수동으로 만든 jpaconfig 파일을 읽어오지는 않기 때문에 이 아래의 import 어노테이션을 이용해서 해당 파일 정보를 읽어올 수 있게 해야 함. 테스트 끝나면 롤백도 됨(일단은)
@Import(Ex01_3_JpaConfig.class) // 테스트파일에서 jpa auditing 구성정보 알아보게 하기.
class Ex01_6_JpaRepositoryTest {
//  @Autowired Ex01_4_ArticleRepository articleRepository;
//  @Autowired ArticleCommentRepository articleCommentRepository;

  // 생성자 주입
  ArticleRepository articleRepository;
  ArticleCommentRepository articleCommentRepository;
  public Ex01_6_JpaRepositoryTest(@Autowired ArticleRepository articleRepository, @Autowired ArticleCommentRepository articleCommentRepository){
    this.articleRepository = articleRepository;
    this.articleCommentRepository = articleCommentRepository;
  }
  //**이름만 보고도 뭔지 알게 이름 짓기**
  /* given_when_then 패턴 만들고 테스트 하기.
   * 세팅 (Ctrl+Alt+S)에서 live templates 검색
   * + 버튼 > Template group 으로 그룹 생성 후 live templates으로 단축키 추가(gw=given whenthen, gwt=given when then)
   * define => comments, consumer function, expression, statement 체크.
   * */

  /* 트랜잭션(transaction) 시 사용하는 메서드
   * 사용법: repository이름.findAll(Sort.by(정렬기준, "정렬기준 컬럼명")
   * 1) findAll() - 모든 컬럼을 조회할 때 사용함. select*from. 페이지 가능.
   * 당연히 select 작업을 하지만, 잠깐 사이에 해당 데이터에 어떤 변화가 있었는지 알수 없다.
   * 그래서 항상 select 하기 전에 update하고 동작시켜야 함.
   *
   * 2) findById()  - 한번에 한 건에 대한 데이터 조회.
   * primary key로 조회한다.
   *
   * 3) save() - 저장(insert, update)
   * 4) count() - 레코드 개수 뽑을 때 사용함.
   * 5) delete() - 삭제(delete).
   *
   * */
  @DisplayName("select 테스트")
  @Test
  void selectTest(){

    //given//어떤 조건 걸지


    //when//어떨 때. 어떤 값이 올 때.
    List<Article> articles = articleRepository.findAll(); // selectAll?

    //then//결과
    assertThat(articles).isNotNull().hasSize(100);

  }

  /* insert 테스트 */

//  @Test
//  @DisplayName("insert 테스트")
//  void insertTest(){
//    // given - 기존 카운트 구하기
//    long prevCount = articleRepository.count();//카운트가 몇 개나 될지 모르니까 long으로
//
//    // when- 삽입 - DB에 삽입하는 순서: DTO에 title, content, hashtag 담아서 넘기기.
//    Article article = Article.of("제목1", "내용1", "Red");
//
//    Article saveArticle = articleRepository.save(article);
//
//    // then - 현재 카운트가 기존 카운트 +1 이면 테스트 통과
//    assertThat(articleRepository.count()).isEqualTo(prevCount+1);
//  }
//
//  /*update 테스트 */
//  @Test
//  @DisplayName("update 테스트")
//  void updateTest(){
//    /* 기존 데이터가 있어야 하고, 그걸 수정했을 때 확인해야 함.
//    * 1) 기존의 영속성 컨텍스트 (persistence context)
//    *   - pc에서 한 개의 엔티티(객체)를 가져오기.
//    * 2) 업데이트.
//    */
//
//    //given
//    /* 순서 1) 기존의 영속성 컨텍스트로부터 엔티티 한 개 가져오기 ->
//    *     2) 글번호 1번은 보통 무조건 있으니까 -> findById(1L)
//    *     3) 없으면 throw 시켜서 일단 현재 테스트는 끝나게 하기 => .orElseThrow() */
//    Article article = articleRepository.findById(1L).orElseThrow();
//System.out.println("여기 !!!!---------------------" + article);
//    /* 순서 2) 해쉬태그 업데이트 하기
//     * 1) */
//    String updateHashtag = "Blue"; // 변수에 업데이트할 문자열 넣고
//    article.setHashtag(updateHashtag); // 엔티티에 있는 setter 를 이용해서 변수를 업데이트한다.
//    //when - 테스트 해야 하는 내용
//    /* flush - push 와 비슷하다.
//    영속성 컨텍스트로부터 가져온 데이터를 그냥 save만 하고 아무것도 안 하고 끝내버리면 어차피 롤백 됨.
//    테스트를 돌리면 run 탭의 마지막 메시지는 select 구문이 나온다.
//    그래서 save 하고 flush 해줘서 해당 필요한 구문까지만 나오게 하기.
//
//    ** saveAndFlush: 바로 DB에 적용하는 방식
//    *  - flush 동작 과정 -
//    * 1. 변경점 감지
//    * 2. 수정된 Entity(여기서는 article) 를 지연 sql 저장소에 등록한다.
//    * 3. 쓰기 지연 sql 저장소의 쿼리를 DB에 전송한다.(등록, 수정, 삭제 쿼리) */
//    Article savedArticle = articleRepository.saveAndFlush(article);//save로 써도 pk가 null 이면 insert, pk가 null 이 아니면 update 로 알아서 해줌.
//    //then
//    //savedArticle 이 "hashtag" 필드를 가지고 있는데, 그 필드에 updateHashtag 값이 있는지 확인하라는 뜻
//    assertThat(savedArticle).hasFieldOrPropertyWithValue("hashtag", updateHashtag);
//  }
//
//  /* delete 테스트 */
//  @DisplayName("delete 테스트")
//  @Test
//  void deleteTetst(){
//    /* 기존에 데이터 있어야 하고 값을 하나 꺼내서 지우기
//    * 1) 기존의 영속성 컨테트스로부터 엔티티 가져오기
//    * 2) 지우면 DB 개수 하나 줄어드는 거니까 미리 엔티티 개수 구해놓기(count)
//    * 3) 하나 삭제
//    * 4) (2번에서 구한 개수 - 1)한 거랑 새로 구한 count 비교해서... 같으면 통과*/
//    //given
//    Article prevArticle = articleRepository.findById(1L).orElseThrow();
//    /* 지우면 DB 개수 하나 줄어드는 거니까 미리 엔티티 개수 구하기
//    * 게시글 뿐만 아니라 연관된 댓글까지 삭제할 거라서...*/
//    long prevArticleCount = articleRepository.count(); //
//    long prevArticleCommentCount = articleCommentRepository.count();
//
//    long deleteCommentSize = prevArticle.getArticleComment().size();
//
//    //when
//    articleRepository.delete(prevArticle);
//    //then
//    /* 현재 게시글 개수(articleRepository.count())가 아까 구한 prevArticleCount보다 1적으면 테스트 통과*/
//    assertThat(articleRepository.count()).isEqualTo(prevArticleCount - 1);
//
//    //
//    assertThat(articleCommentRepository.count()).isEqualTo(prevArticleCommentCount - deleteCommentSize);
//  }
//
//
//
//  @DisplayName("select 테스트")
//  @Test
//  void mySelectTest(){
//    List<Article> list = articleRepository.findAll();
//
//    assertThat(list).isNotNull().hasSize(100);
//  }
//
//  @DisplayName("insert 테스트")
//  @Test
//  void myInsertTest(){
//    long prevArticle = articleRepository.count();
//    Article article = Article.of("제목", "내용", "Red");
//
//    articleRepository.save(article);
//
//    assertThat(articleRepository.count()).isEqualTo(prevArticle + 1);
//  }
//
//  @DisplayName("update 테스트")
//  @Test
//  void myUpdateTest(){
//    Article article = articleRepository.findById(1L).orElseThrow();
//
//    String updateHash = "Blue";
//    article.setHashtag(updateHash);
//
//    articleRepository.saveAndFlush(article);
//
//    assertThat(article).hasFieldOrPropertyWithValue("hashtag", updateHash);
//  }
//
//  @DisplayName("delete 테스트")
//  @Test
//  void myDeleteTest(){
//    //지울 거 갖고 오기
//    Article article = articleRepository.findById(1L).orElseThrow();
//
//    long prevArticleCount = articleRepository.count();
//    long prevArticleCommentCount = articleCommentRepository.count();
//
//    long deleteArticleComment = article.getArticleComment().size();
//
//    articleRepository.delete(article);
//
//    assertThat(articleRepository.count()).isEqualTo(prevArticleCount - 1);
//    assertThat(articleCommentRepository.count()).isEqualTo(prevArticleCommentCount - deleteArticleComment);
//  }
}