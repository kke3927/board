package com.bitstudy.board.dto;

import com.bitstudy.board.domain.Article;
import com.bitstudy.board.domain.ArticleComment;

import java.time.LocalDateTime;

public record ArticleCommentDto(
        Long id,
        Long articleId,/*아티클 전체가 아니라 아티클 번호만 가져오기*/
        UserAccountDto userAccountDto,
        String content,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {
    public static ArticleCommentDto from(ArticleComment entity){
      return new ArticleCommentDto(
              entity.getId()
              , entity.getArticle().getId()
              , UserAccountDto.from(entity.getUserAccount())
              , entity.getContent()
              , entity.getCreatedAt()
              , entity.getCreatedBy()
              , entity.getModifiedAt()
              , entity.getModifiedBy());
    }

    public ArticleComment toEntity(Article entity){
      return ArticleComment.of(entity, userAccountDto.toEntity(), content);
    }
}
