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

@RepositoryRestResource
public interface ArticleRepository extends JpaRepository<Article, Long>, QuerydslPredicateExecutor<Article>/*얘만 있어도 검색은 됨. 정확한 검색만 가능*/, QuerydslBinderCustomizer<QArticle>/*like 검색*/  {

  
  /* QuerydslBinderCustomizer<QArticle> 사용하려면 customize 메서드 필요함. 
  * customize 오버라이드 하는 방법
  * 1) Ctrl + O  > void customize(QuerydslBindings bindings, QArticle root) 선택
  * 2)
  *
  * *customize : 세부적인 규칙을 재구성할 수 있는 메서드. java16 버전 이상일 때만 가능한 메서드.
  *             원래는 여기가 interface 라서 구현부를 만들 수 없는데,
  *             java 1.8 이후부터는 default 를 써서 구현부를 만들 수 있음(많이 쓰면 안 됨)
  *
  * */
  @Override
  default void customize(QuerydslBindings bindings, QArticle root) {
    bindings.excludeUnlistedProperties(true); // 리스트를 만들 건데 그 리스트에 있는 컬럼만 검색되게 하라는 뜻
    // ex) localhost:8080/api?키=값
    /* 원하는 필드를 추가하는 부분 */
    bindings.including(root.title, root.content, root.hashtag, root.createdAt, root.createdBy);

//    bindings.bind(root.title).first(StringExpression::likeIgnoreCase);//쿼리상 "like '${문자열}'"로 들어감
    bindings.bind(root.title).first(StringExpression::containsIgnoreCase);//쿼리상 like '%문자열%'
    bindings.bind(root.content).first(StringExpression::containsIgnoreCase);
    bindings.bind(root.hashtag).first(StringExpression::containsIgnoreCase);
    bindings.bind(root.createdAt).first(DateTimeExpression::eq);// 날짜 DateTimeExpression, eq : equals의 뜻.(완벽하게 같은 날짜만 검색되도록 할 것이다.)
    // 이렇게 하면 시분초를 다 0으로 인식하기 때문에 조심해야 함.
    bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);
  }
}