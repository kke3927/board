package com.bitstudy.board.repository;

import com.bitstudy.board.config.Ex01_3_JpaConfig;
import com.bitstudy.board.domain.Article;
import com.bitstudy.board.domain.UserAccount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@Import(Ex01_3_JpaConfig.class)
class Ex08_8_JpaRepositoryTest {

  // 생성자 주입
  ArticleRepository articleRepository;
  ArticleCommentRepository articleCommentRepository;
  public static UserAccountRepository userAccountRepository;

  public Ex08_8_JpaRepositoryTest(@Autowired ArticleRepository articleRepository, @Autowired ArticleCommentRepository articleCommentRepository){
    this.articleRepository = articleRepository;
    this.articleCommentRepository = articleCommentRepository;
  }
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

  @Test
  @DisplayName("insert 테스트")
  void insertTest(){
    // given - 기존 카운트 구하기
    long prevCount = articleRepository.count();//카운트가 몇 개나 될지 모르니까 long으로

    // when- 삽입 - DB에 삽입하는 순서: DTO에 title, content, hashtag 담아서 넘기기.
    UserAccount userAccount = userAccountRepository.save(UserAccount.of("new bitstudy", "1234", null, null, null));
    Article article = Article.of(userAccount, "제목1", "내용1", "Red");

    Article saveArticle = articleRepository.save(article);

    // then - 현재 카운트가 기존 카운트 +1 이면 테스트 통과
    assertThat(articleRepository.count()).isEqualTo(prevCount+1);
  }

  /*update 테스트 */
  @Test
  @DisplayName("update 테스트")
  void updateTest(){


    //given

    Article article = articleRepository.findById(1L).orElseThrow();
System.out.println("여기 !!!!---------------------" + article);
    /* 순서 2) 해쉬태그 업데이트 하기
     * 1) */
    String updateHashtag = "Blue";
    article.setHashtag(updateHashtag);
    //when - 테스트 해야 하는 내용

    Article savedArticle = articleRepository.saveAndFlush(article);//save로 써도 pk가 null 이면 insert, pk가 null 이 아니면 update 로 알아서 해줌.
    //then
    //savedArticle 이 "hashtag" 필드를 가지고 있는데, 그 필드에 updateHashtag 값이 있는지 확인하라는 뜻
    assertThat(savedArticle).hasFieldOrPropertyWithValue("hashtag", updateHashtag);
  }

  /* delete 테스트 */
  @DisplayName("delete 테스트")
  @Test
  void deleteTetst(){
    //given
    Article prevArticle = articleRepository.findById(1L).orElseThrow();
    /* 지우면 DB 개수 하나 줄어드는 거니까 미리 엔티티 개수 구하기
    * 게시글 뿐만 아니라 연관된 댓글까지 삭제할 거라서...*/
    long prevArticleCount = articleRepository.count(); //
    long prevArticleCommentCount = articleCommentRepository.count();

    long deleteCommentSize = prevArticle.getArticleComment().size();

    //when
    articleRepository.delete(prevArticle);
    //then

    assertThat(articleRepository.count()).isEqualTo(prevArticleCount - 1);

    //
    assertThat(articleCommentRepository.count()).isEqualTo(prevArticleCommentCount - deleteCommentSize);
  }



  @DisplayName("select 테스트")
  @Test
  void mySelectTest(){
    List<Article> list = articleRepository.findAll();

    assertThat(list).isNotNull().hasSize(100);
  }

  @DisplayName("insert 테스트")
  @Test
  void myInsertTest(){
    long prevArticle = articleRepository.count();
    UserAccount userAccount = userAccountRepository.save(UserAccount.of("new bitstudy", "1234", null, null, null));
    Article article = Article.of(userAccount, "제목", "내용", "Red");

    articleRepository.save(article);

    assertThat(articleRepository.count()).isEqualTo(prevArticle + 1);
  }

  @DisplayName("update 테스트")
  @Test
  void myUpdateTest(){
    Article article = articleRepository.findById(1L).orElseThrow();

    String updateHash = "Blue";
    article.setHashtag(updateHash);

    articleRepository.saveAndFlush(article);

    assertThat(article).hasFieldOrPropertyWithValue("hashtag", updateHash);
  }

  @DisplayName("delete 테스트")
  @Test
  void myDeleteTest(){
    //지울 거 갖고 오기
    Article article = articleRepository.findById(1L).orElseThrow();

    long prevArticleCount = articleRepository.count();
    long prevArticleCommentCount = articleCommentRepository.count();

    long deleteArticleComment = article.getArticleComment().size();

    articleRepository.delete(article);

    assertThat(articleRepository.count()).isEqualTo(prevArticleCount - 1);
    assertThat(articleCommentRepository.count()).isEqualTo(prevArticleCommentCount - deleteArticleComment);
  }
}