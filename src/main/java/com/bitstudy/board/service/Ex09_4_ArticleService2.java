package com.bitstudy.board.service;


import com.bitstudy.board.domain.type.SearchType;
import com.bitstudy.board.dto.ArticleDto;
import com.bitstudy.board.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor // 필수 필드 생성자를 자동으로 만들어준다.
@Transactional
public class Ex09_4_ArticleService2 {
    public final ArticleRepository articleRepository;
    @Transactional(readOnly = true)
    public Page<ArticleDto> searchArticles(SearchType searchType, String searchKeyword, Pageable pageable){
        if(searchKeyword == null || searchKeyword.isBlank()){
            return articleRepository.findAll(pageable).map(ArticleDto::from);
        }
        return switch (searchType) {
            case TITLE -> articleRepository.findByTitleContaining(searchKeyword, pageable).map(ArticleDto::from);
            case CONTENT -> articleRepository.findByContentContaining(searchKeyword, pageable).map(ArticleDto::from);
            case ID -> articleRepository.findByUserAccount_UserIdContaining(searchKeyword, pageable).map(ArticleDto::from);
            case NICKNAME -> articleRepository.findByUserAccount_NicknameContaining(searchKeyword, pageable).map(ArticleDto::from);
            case HASHTAG -> articleRepository.findByHashtagContaining("#"+searchKeyword, pageable).map(ArticleDto::from);
        };
    }



    public ArticleDto searchArticle(Long l) {
        return null; // 아직 서비스 로직이 구현되어 있지 않았으니까 일단 null로 테스트 실패하게 만들기.
    }

//    @Transactional(readOnly = true)

}
