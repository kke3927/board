package com.bitstudy.board.domain.type;

/* enum
* 상수(final)의 모음
* getter, setter, 롬복 없어도 만들어줌
* 대신 getter 이름이 getTitle 이런 식으로 하지 않고 그냥 변수처럼 생긴 이름을 사용한다.*/
public enum SearchType {
  TITLE, CONTENT, ID, NICKNAME, HASHTAG
}
