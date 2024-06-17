package com.bitstudy.board.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/articles")
public class ArticleController {
  @GetMapping
  public String articles(ModelMap model) {
    model.addAttribute("articles", List.of());
    return "articles/index";
  }

  @GetMapping("/{articleId}")
  public String article(@PathVariable Long articleId, ModelMap model) {
    model.addAttribute("articles", "1");
    //나중에 테스트할 때 null 대신 다른 문구 넣을 것.
    //원래는 null 대신 Article 객체가 들어와야 하는데 도메인코드 Article을 노출시키진 않을 예정. 나중에 DTO 만들어서 넣을 것.
    //만약 TDD인 경우, null 은 테스트 못함. 하려면 null 대신 아무 문자열 넣어야 함.
    model.addAttribute("articleComments", List.of());

    return "articles/detail";
  }
}


