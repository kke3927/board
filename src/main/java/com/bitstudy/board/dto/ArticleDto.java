package com.bitstudy.board.dto;

import com.bitstudy.board.domain.Article;
import com.bitstudy.board.domain.UserAccount;

import java.time.LocalDateTime;

public record ArticleDto(// 우선 엔티티가 가지고 있는 모든 정보들을 dto도 가지고 있게 해서 나중에 필요할때 가공할거임
        Long id,
        UserAccountDto userAccountDto,
        String title,
        String content,
        String hashtag,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy) {
  public static ArticleDto of(
          Long id,
          UserAccountDto userAccountDto,
          String title,
          String content,
          String hashtag,
          LocalDateTime createdAt,
          String createdBy,
          LocalDateTime modifiedAt,
          String modifiedBy){
    return new ArticleDto(id, userAccountDto, title, content, hashtag, createdAt, createdBy, modifiedAt, modifiedBy);
  }

  public static ArticleDto from(Article entity){
    return new ArticleDto(
            entity.getId(),
            UserAccountDto.from(entity.getUserAccount()),
            entity.getTitle(),
            entity.getContent(),
            entity.getHashtag(),
            entity.getCreatedAt(),
            entity.getCreatedBy(),
            entity.getModifiedAt(),
            entity.getModifiedBy());
  }

  public Article toEntity(){
    return Article.of(
            userAccountDto.toEntity(),
            title,
            content,
            hashtag
    );
  }
}
