package com.bitstudy.board.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/** 실패하는 테스트 만들기 */
/* 컨트롤러에 대한 테스트 (슬라이스 테스트)
* 슬라이스 테스트: 레이어별로 잘라서, 특정 부분만 테스트 하는 것.
*
* -대표적인 통합 테스트 어노테이션
*   1) @SpringBootTest - 스프링이 관리하는 모든 빈을 등록시킨 후에 테스트함.
*   (무거운 테스트)
* -대표적인 슬라이스 테스트 어노테이션
*   1) @WebMvcTest - Controller 테스트할 때 주로 사용함.
*                   @WebMvcTest 를 선언하면 Web과 관련된 Bean만 Injection 되고, MockMvc 를 담아놓은 가짜 데이터를 알아볼 수 있게 된다.
*   *MockMvc 는 웹 어플리케이션을 서버에 배포하지 않고, 테스트용 Mvc 환경을 만들어서 요청 및 전송한다.
*   * 쉽게 말하면, 개발자가 컨트롤러 테스트를 하고 싶을 때 실제 서버에 구현된 어플리케이션을 올리지 않고 테스트용 시뮬레이션을 돌려서 MVC 가 되도록 도와주는 클래스이다.
*
*   * 컨트롤러 테스트 => @WebMvcTest 사용.
*
*   2) @DataJpaTest - JPA 레포지토리 테스트할 때 사용한다.
*                     @Entity 가 있는 엔티티 클래스들을 스캔해서 테스트를 위한 환경을 설정한다.
*                     @Component 같은 Bean 들은 스캔되지 않음.
*   3) @RestClientTest - 클라이언트 입장에서의 API 연동 테스트.
*                       테스트 코드 내에서 Mock 서버를 띄울 수 있게 된다.
* */
@WebMvcTest // 슬라이스 테스트. 컨트롤러 관련된 Bean 들만 로드함.
// 이 어노테이션은 컨트롤러와 연관된 내용만 최소한으로 로드하기 때문에 data rest의 autoConfiguration 을 읽지는 않는다.
public class Ex04_1_DataRestRepositoryTest {
  /* MockMvc 테스트 방법
  * 1) MockMvc 생성(빈 준비)
  * 2) MockMvc 에 요청에 대한 정보를 입력
  * 3) 요청에 대한 응답값을 Expect 를 이용해서 테스트함.
  * 4) Expect 가 모두 통과하면 통과
  *     하나라도 실패하면 실패
  *
  * Hal 에서 url을 돌려보면 동작하는데 여기서는 왜 안 되냐면,
  * @WebMvcTest는 슬라이스 테스트이기 때문에 Controller 이외의 빈은 아예 로그하지 않기 때문이다.
  * 그래서 이런 경우에는 @WebMvcTest 대신 통합테스트를 사용할 것임.
  * */
  private final MockMvc mockMvc;

  public Ex04_1_DataRestRepositoryTest(@Autowired MockMvc mockMvc) {
    this.mockMvc = mockMvc;
  }

  @DisplayName("[api] 게시글 리스트 조회")
  @Test
  void test1() throws Exception {
    // given

    // when & then
    /**/
    mockMvc.perform(get("/api/articles"))
            .andExpect(status().isOk())//현재상태가 200인지 묻기
            .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));//가져온 데이터 타입 묻기

    /* get() 작성하는 법
    * 1) perform() 안에 get 친다.
    * 2) getClass()만 뜰 때 ctrl + space 누른다.
    * 3) 추천 리스트가 뜨는데 그 중에 MockMvc~ 가 뜨면 바로 enter 누르지 말고
    * alt + enter 눌러서 static 으로 import 해 온다.
    * 윈도우: alt + enter
    * 맥: 옵션 + enter?
    * 4) 이렇게 하면 get() 부분만 가져와진다. => static import 방식.
    *   3까지 하면 맨 위에 import 부분에 import static~~ 생긴다.
    * 5) status()도 이와 같은 방법으로 불러온다. MockMvcResultMatchers.status; 선택.(alt + enter)
    *
    * 6) content()는 그냥 일반적으로 하면 됨.
    *
    * */
  }
}
