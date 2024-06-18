package com.bitstudy.board.dto;

import com.bitstudy.board.domain.Article;
import com.bitstudy.board.domain.UserAccount;

import java.time.LocalDate;
import java.time.LocalDateTime;

/* 레코드 : 데이터 전달을 목적으로 하는 객체를 더 빠르게 만드는 기능
* 상속 할 수 없음!
* **JDK16에서 새로 나온 것.
* DTO와 비슷하지만 DTO를 구현하면 getter, setter, equals, hashCode, toString  같은 데이터 처리나 연산을 하기 위해 오버라이드된 메서드를 반복해서 작성해야 함.
* 롬복을 이용해서 반복되는 코드를 줄였지만 근본적 해결책은 아님.
*  >> 그래서 record 가 나옴.
*   특정 데이터와 관련있는 필드들만 묶어놓은 자료구조형.
*   주의: record는 entity로는 사용 불가.
*
* 사용법:
*     레코드명(헤더), {바디}의 구조를 가지는데 헤더에 나열되는 필드를 컴포넌트라고 부름.
* 레코드는 생성자, toString, equals, hahCode를 선언하지 않아도 알아서 제공해줌.
* 단, getter 사용시 getName()이런식으로 쓰지 말고 대놓고 필드명 사용하면됨.
*
*
* 파라미터(매개변수)에 명시한 필드는 final들임.
* getter, setter를 롬복과 상관없이 자동으로 만들어줌.
* */
public record UserAccountDto (
        Long id,
        String userId,
        String userPassword,
        String email,
        String nickname,
        String memo,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy){

  public static UserAccountDto of(
          Long id, String userId, String userPassword, String email, String nickname, String memo, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
    return new UserAccountDto(id, userId, userPassword, email, nickname, memo, createdAt, createdBy, modifiedAt, modifiedBy);
  }

  public static UserAccountDto from(UserAccount entity) {
    return  new UserAccountDto(entity.getId(), entity.getUserId(), entity.getUserPassword(), entity.getEmail(), entity.getNickname(), entity.getMemo(), entity.getCreatedAt(), entity.getCreatedBy(), entity.getModifiedAt(), entity.getModifiedBy()
    );
  }

  public UserAccount toEntity(){
    return UserAccount.of(userId, userPassword, email, nickname, memo);
  }
}
