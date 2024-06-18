package com.bitstudy.board.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;


//@Configuration
//@EnableJpaAuditing
public class JpaConfig {
//  @Bean
  public AuditorAware<String> auditorAware(){
    // 람다식...??(스크립트에서의 화살표 함수 같은 거. 익명 함수)
    return ()-> Optional.of("bitstudy");
    // 이렇게 하면 앞으로 JPA Auditing 할 때마다 사람 이름은 bitstudy로 넣게 된다.
    // TODO : 나중에 스프링 시큐리티로 인증 기능 붙일 때 수정할 것이다...!
  }
}
