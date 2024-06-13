package com.bitstudy.board.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

/* @Configuration 이라고 하면, JPA가 설정파일이라고 인식해서  configuration Bean 으로 등록한다. */
@Configuration
@EnableJpaAuditing
/* jpa 에서 auditing 을 가능하게 하느 어노테이션.
* auditing : 감시, 감사
* Spring Data Jpa 에서 자동으로 값을 넣어주는 기능이다.
* 컬럼을 지켜보고 있다가? 자동으로 값을 넣어주는... 그런 느낌!*/
public class Ex01_3_JpaConfig {
  @Bean
  public AuditorAware<String> auditorAware(){
    // 람다식...??(스크립트에서의 화살표 함수 같은 거. 익명 함수)
    return ()-> Optional.of("bitstudy");
    // 이렇게 하면 앞으로 JPA Auditing 할 때마다 사람 이름은 bitstudy로 넣게 된다.
    // TODO : 나중에 스프링 시큐리티로 인증 기능 붙일 때 수정할 것이다...!
  }
}
