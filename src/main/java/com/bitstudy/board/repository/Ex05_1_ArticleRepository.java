package com.bitstudy.board.repository;

import com.bitstudy.board.domain.Article;
import com.bitstudy.board.domain.QArticle;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@RepositoryRestResource
public interface Ex05_1_ArticleRepository extends JpaRepository<Article, Long>, QuerydslPredicateExecutor<Article>/*얘만 있어도 검색은 됨. 정확한 검색만 가능*//*, QuerydslBinderCustomizer<QArticle>*//*like 검색*/ {
  /*QuerydslPredicateExecutor 는 기본적으로 article 안에 있는 모든 필드에 대한 기본 검색 기능을 추가해줌 (이거만 있어도 검색은 가능함.)
  * - 간단한 테스트 해보기 위해서 QuerydslBinderCustomizer<QArticle> 주석처리 해보고
  * QuerydslPredicateExecutor<Article> 만 살려서 해보기
  * 
  * 1) 주석처리 하고 BoardApplication 실행하기
  * 2) HAL 가서 아무 Article 하나 들어가서 ? 뒤에 정보 넣어서 검색해보기
  * api/articles?hashtag=Yello
  * 이렇게 하면 hashtag가 정확히 Yellow인 것만 나온다.
  * 철자가 하나라도 다르면 아무것도 안 나옴. */


}
