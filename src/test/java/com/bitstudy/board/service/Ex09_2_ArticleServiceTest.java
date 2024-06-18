package com.bitstudy.board.service;

import com.bitstudy.board.domain.type.SearchType;
import com.bitstudy.board.dto.ArticleDto;
import com.bitstudy.board.repository.ArticleRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.util.List;

import static  org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


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
class Ex09_2_ArticleServiceTest {
    /* mock 을 주입하는 거에 @InjectMocks 달아주기 (그 외에는 @Mock을 달아준다.
    *  Mock 을 주입하는 대상에는 @ InjectionMocks를 줘야 함*/
  @InjectMocks
  private ArticleService sut; // sut - system under test
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

    //when
//    List<ArticleDto> articles = sut.searchArticles(SearchType.TITLE, "검색어");

    Page<ArticleDto> articles = sut.searchArticles(SearchType.TITLE, "검색어");

    /* List: list 는 컬렉션, 요소들에 순서가 있고 중복 허용된다.
    * 일반적으로 모든 요소들을 메모리에 저장함. 만약 데이터가 1000개 있으면 10개만 필요해도 한번에 1000개 다 가져와서 메모리에 저장한다. => 작은 데이터셋에 대한 작업시 유용하다.
    *
    * Page : 대량의 데이터를 페이징해서 처리할 때 사용한다.
    * 데이터를 작은 페이지 단위로 나눠서 읽고 처리함.(한번에 모든 데이터를 로드하지 않고 필요한 만큼의 데이터만 메모리에 로드함. 그래서 필요없는 메모리 낭비가 없어서 유용하다.
    * => 큰 데이터셋에 대한 작업시 유용하다.
    * Page 는 일반적으로 페이지번호, 페이지크기, 총 개수 등의 정보를 가지고 있음.*/
    //then
    assertThat(articles).isNotNull();
  }


  /* 2. 각 게시글 페이지로 이동 */
  @Disabled("구현중")
  @DisplayName("검색어 없이 게시글 검색하면 게시글 페이지 반환")
  @Test
  void selectOne(){
    //given - 게시글 하나 불러올 때는 해당 글의 id(글번호)로 검색해야 함.
    ArticleDto articles =  sut.searchArticle(1L);
    //when

    //then
//    assertThat(articles).isNotNull();
    assertThat(articles).isNull();
  }


}