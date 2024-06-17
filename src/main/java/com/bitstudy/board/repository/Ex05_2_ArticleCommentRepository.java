package com.bitstudy.board.repository;

import com.bitstudy.board.domain.Article;
import com.bitstudy.board.domain.ArticleComment;
import com.bitstudy.board.domain.QArticle;
import com.bitstudy.board.domain.QArticleComment;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
//@RepositoryRestResource
public interface Ex05_2_ArticleCommentRepository extends JpaRepository<ArticleComment, Long>/*, QuerydslPredicateExecutor<ArticleComment>*//*얘만 있어도 검색은 됨. 정확한 검색만 가능*//*, QuerydslBinderCustomizer<QArticleComment>*//*like 검색*/ {
  /*QuerydslPredicateExecutor 는 기본적으로 article 안에 있는 모든 필드에 대한 기본 검색 기능을 추가해줌 (이거만 있어도 검색은 가능함.) */
}
