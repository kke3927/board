package com.bitstudy.board.service;


import com.bitstudy.board.domain.type.SearchType;
import com.bitstudy.board.dto.ArticleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor // 필수 필드 생성자를 자동으로 만들어준다.
@Transactional
public class ArticleService {
//    public List<ArticleDto> searchArticles(SearchType title, String keyword) {
//        return List.of();
//    }
    @Transactional(readOnly = true)
    public Page<ArticleDto> searchArticles(SearchType title, String keyword){
        return Page.empty();
    }
    public ArticleDto searchArticle(Long l) {
        return null; // 아직 서비스 로직이 구현되어 있지 않았으니까 일단 null로 테스트 실패하게 만들기.
    }
}
