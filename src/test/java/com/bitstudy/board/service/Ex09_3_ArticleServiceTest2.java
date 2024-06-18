package com.bitstudy.board.service;

import com.bitstudy.board.domain.Article;
import com.bitstudy.board.domain.UserAccount;
import com.bitstudy.board.domain.type.SearchType;
import com.bitstudy.board.dto.ArticleDto;
import com.bitstudy.board.dto.UserAccountDto;
import com.bitstudy.board.repository.ArticleRepository;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;


/** 서비스 비지니스 로직은 스프링 부트의 슬라이스 테스 기능 사용하지 않고 작성해볼 거임.
 * (어플리케이션 컨텍스트가 뜨는데 걸리는 시간을 없애기 위해 가볍게 만든 테스트이다. )
 *
 * 가능한 가볍게 만들기 위해 불필요한 스프링부트 어플리케이션을 띄우는 걸 생략하는 대신
 * 디펜던시 추가되어야 하는 게 있으면 Mocking 하는 방식으로 해볼 거임.
 *
 * 그걸 가능하게 해주는 라이브러리가 있음. (mokito) 설치할 필요가 없음.
 * @ ExtendWith(MockitoExtension.class) 어노테이션 필요
 * junit 안 쓰고 assertJ
 * */

@ExtendWith(MockitoExtension.class)
class Ex09_3_ArticleServiceTest2 {
    /* mock 을 주입하는 거에 @InjectMocks 달아주기 (그 외에는 @Mock을 달아준다.
    *  Mock 을 주입하는 대상에는 @ InjectionMocks를 줘야 함*/
  @InjectMocks
  private Ex09_4_ArticleService2 sut; // sut - system under test
  // 실무에서 테스트 짤 때 '이건 테스트 대상이다' 라는 뜻.

  @Mock
  private ArticleRepository articleRepository; // 의존하는 걸 가져와야 됨. (mocking ) 할 때 필요함.

  /* 테스트할 항목들
  * 1. 검색
  * 2. 각 게시글 페이지로 이동
  * 3. 페이지네이션
  * 4. 홈버튼 클릭하면 게시판 페이지로 아동(컨트롤러에서 하기)
  * */
  /*1. 검색 */
  @DisplayName("검색어 없이 게시글 검색하면 게시글 페이지 반환")
  @Test
  void articleAll(){
    //given
    Pageable pageable = Pageable.ofSize(20);
      //한 페이지에 몇 개 가져올지 결정
    given(articleRepository.findAll(pageable)).willReturn(Page.empty());
      /*Page: 전체 데이터 건수를 조회하는 count 쿼리 결과를 포함하는 페이징.
      * 데이터를 다 가져오기 때문에
      * 1) getTotalElements() 를 이용해서 개수를 뽑을 수도 있고,
      * 2) getTotalPages() 메서드에 별도의 size를 줘서 총 페이지 개수도 구할 수 있음.
      * 3) getNumber()를 이용해서 가져온 페이지의 번호를 뽑을 수 있음.
      * Pageable: 페이징 기능.
      *           Spring JPA에서 limit 쿼리를 날려서 데이터 가져옴.
      * */
    //when - 매개변수가 없는 경우


//    List<ArticleDto> articles = sut.searchArticles(SearchType.TITLE, "검색어");

    Page<ArticleDto> articles = sut.searchArticles(SearchType.TITLE, "검색어", pageable);


    //then
    assertThat(articles).isNotNull();
  }


  /* 2. 각 게시글 페이지로 이동 */
  @Disabled("구현중")
  @DisplayName("게시글 조회하면 게시글 하나를 반환한다.")
  @Test
  void selectOne(){
    //given - 게시글 하나 불러올 때는 해당 글의 id(글번호)로 검색해야 함.
    Long articleId = 1L;

    Article article = createArticle();
    given(articleRepository.findById(articleId)).willReturn(Optional.of(article));

    //when

    //then
//    assertThat(articles).isNotNull();
    assertThat(article).isNull();
  }

  private UserAccount createUserAccount(){
    return UserAccount.of("bitstudy", "password", "bitstudy@email.com", "bitstudy", null);
  }

  private Article createArticle(){
    return Article.of(createUserAccount(), "title", "content", "hashtag");
  }

  private ArticleDto createArticleDto(){
    return createArticleDto("title", "content", "#Spring");
  }

  private ArticleDto createArticleDto(String title, String content, String hashtag) {
    return ArticleDto.of(1L, createUserAccountDto(), title, content, hashtag, LocalDateTime.now(), "bitsutdy", LocalDateTime.now(), "bitstudy");
  }

  private UserAccountDto createUserAccountDto(){
    return UserAccountDto.of(1L, "bitstudy", "1234", "bitstudy@email.com", "bitstudy", "memeo", LocalDateTime.now(), "bitstudy", LocalDateTime.now(), "bitstudy");
  }
}

