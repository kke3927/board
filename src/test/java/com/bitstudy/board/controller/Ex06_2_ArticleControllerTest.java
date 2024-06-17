package com.bitstudy.board.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
/* 할일: @Disabled 어노테이션 사용법 알아두기
 * 하기 전에 알아두기 : 테스트 코드 작성할 건데 일단 돌리면 404 에러남.
 * 이유는 06_1_ArticleController 에 내용이 없고, dao 같은 것도 없어서 그렇다.
 *
 *  */


//WebMvcTest 만 쓰면 모든 컨드롤러들을 다 읽어들인다.

@WebMvcTest(ArticleController.class)
@DisplayName("view 컨트롤러 테스트 - 게시글")
class Ex06_2_ArticleControllerTest {
  private final MockMvc mockMvc;

  Ex06_2_ArticleControllerTest(@Autowired  MockMvc mockMvc) {
    this.mockMvc = mockMvc;
  }

//  @Disabled("구현 중")
  @Test
  @DisplayName("[view][GET] 게시글리스트 (게시판) 페이지 - 정상호출")
  public void articleAll() throws Exception {
    mockMvc.perform(get("/articles"))// /articles 가서 정보 얻어와라
            .andExpect(status().isOk()) // 200이 뜨면 정상
            .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML)) // 뷰파일은 html로 만들기 때문에 데이터 타입은 html
            .andExpect((view().name("articles/index"))) // 현재 정보를 가지고 온 문서 이름이 index 이고 articles 폴더 안에 있는지
            .andExpect(model().attributeExists("articles"));//뷰 파일에서 게시글들의 목록들이 다 떠야 하는데 이는 서버에서 게시글들을 가져왔다는 뜻이다.
    //모델로 데이터를 밀어넣어줬다는 뜻인데 그게 있는지 없는지 검사할 수 있음.
    //해석 : model().attributeExists("articles") 에서 articles는 내가 임의로 준 키값이다. 맵에 articles 라는 키가 있는지 검사하라는 뜻.
  }


  /* 테스트 api 만들건데 엑셀 api 에 있는 순서대로 만들 거임
    1) 게시판
    2) 상세
    3) 검색
    4) 해시태그 검색 전용
  */

//  @Disabled("구현중")
  @Test
  @DisplayName("[view][GET] 게시글 상세 (게시판) 페이지 - 정상호출")
  public void articleOne() throws Exception {
    mockMvc.perform(get("/articles/1"))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
            .andExpect((view().name("articles/detail")))
            .andExpect(model().attributeExists("articles"))
            .andExpect(model().attributeExists("articleComments"));
    // 상세페이지에는 댓글들도 여러개 있을 수 있으니 모델이 articlesComments도 키값으로 있는지 검사
  }

  @Disabled("구현중")
  @Test
  @DisplayName("[view][GET] 게시글 검색 (게시판) 페이지 - 정상호출")
  public void articleSearch() throws Exception {
    mockMvc.perform(get("/articles/search"))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
            .andExpect((view().name("articles/search")));
//            .andExpect(model().attributeExists("search"));
    // 이 테스트에서는 검색 페이지만 뿌려주면 되는 거라서 아직 데이터를 받아오지 않은 상태라 필요 없음.
  }

  @Disabled("구현중")
  @Test
  @DisplayName("[view][GET] 게시글 해시태그 검색 전용 페이지 - 정상호출")
  public void articleSearchHashtag() throws Exception {
    mockMvc.perform(get("/articles/search-hashtag"))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
            .andExpect((view().name("articles/search-hashtag")));
//            .andExpect(model().attributeExists("search"));
    // 이 테스트에서는 검색 페이지만 뿌려주면 되는 거라서 아직 데이터를 받아오지 않은 상태라 필요 없음.
  }
}