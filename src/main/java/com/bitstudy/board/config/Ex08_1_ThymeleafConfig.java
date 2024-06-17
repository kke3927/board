package com.bitstudy.board.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;

//@Configuration
public class Ex08_1_ThymeleafConfig {
//  @Bean
//  public SpringResourceTemplateResolver thymeleafTemplateResolver(SpringResourceTemplateResolver defaultTemplateResolver, Thymeleaf3Properties thymeleaf3Properties){
    //, Thymeleaf3Properties thymeleaf3Properties

    /* thymeleafTemplateResolver 라는 빈을 등록할 건데, 리턴타입은 SpringResourceTemplateResolver 이다. 그런데 스프링부트 프로젝트에 넣었을 때 auto_configuration 이 자동으로 잡힐 것.*/

    /* 그런데 deCoupled 로직을 세팅하는 거는 이미 만들어져 있음.
    * 그런데 외부 프로퍼티이기 때문에 별도로 인식을 못 한다. 그래서 yaml 파일에 가서 'thymeleaf3:' 부분처럼 설정할 거임.  */
//    defaultTemplateResolver.setUseDecoupledLogic(thymeleaf3Properties.isDecoupledLogic());
//    return defaultTemplateResolver;
  }



//  @RequiredArgsConstructor
//  @Getter
//  @ConfigurationProperties("spring.thymeleaf3") /*prefix로 이름을 thymeleaf3 로 설정함 (yaml 에서 사용할 이름)*/
//  public static class Thymeleaf3Properties {
//    private final boolean decoupledLogic;
//
//    @ConstructorBinding
//    public Thymeleaf3Properties(boolean decoupledLogic) {
//      this.decoupledLogic = decoupledLogic;
//    }
//    public boolean isDecoupledLogic(){
//      return decoupledLogic;
//    }
//  }