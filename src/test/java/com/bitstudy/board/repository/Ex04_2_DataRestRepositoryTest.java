package com.bitstudy.board.repository;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/** 동작하는 테스트 만들기 */
@DisplayName("API test")
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class Ex04_2_DataRestRepositoryTest {

  private final MockMvc mockMvc;
  public Ex04_2_DataRestRepositoryTest(@Autowired MockMvc mockMvc) {
    this.mockMvc = mockMvc;
  }

  @DisplayName("[api] 게시판 리스트 조회")
  @Test
  void test2() throws Exception {
    mockMvc.perform(get("/api/articles"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
  }

  @DisplayName("[api] 게시판 단건 조회")
  @Test
  void articleOne() throws Exception {
    mockMvc.perform(get("/api/articles/1"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
  }

  @DisplayName("[api] 게시판 댓글 전체 조회")
  @Test
  void articleCommentAll() throws Exception {
    mockMvc.perform(get("/api/articles/1/articleComment"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
  }

//  @DisplayName("[api] 게시판 댓글 단일 조회") // ArticleComment에 있는 모든 댓글이 아니라 특정 Article의 댓글들 전체 조회인 거임.
//  @Test
//  void articleCommentOne() throws Exception {
//    mockMvc.perform(get("/api/articles/1/articleComment/1"))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
//  }
}
