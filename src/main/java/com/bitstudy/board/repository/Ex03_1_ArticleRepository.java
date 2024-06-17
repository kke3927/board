package com.bitstudy.board.repository;

import com.bitstudy.board.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
/** @ RepositoryRestResource 를 넣어서 spring rest data를 준비하고 03_2 에서도 똑같이.
 *
 * service 탭에 있는 BoardApplication 실행하고,
 * 브라우저에 localhost:8080/api 치면 HAL Explorer 켜질 것.
 * */

//@RepositoryRestResource /* yaml 파일에서 detection-strategy : annotated 대응하는 어노테이션*/
public interface Ex03_1_ArticleRepository extends JpaRepository<Article, Long> {

}
