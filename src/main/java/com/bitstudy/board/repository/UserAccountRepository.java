package com.bitstudy.board.repository;

import com.bitstudy.board.domain.Article;
import com.bitstudy.board.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

/** 할일: 당장 유저 관련된 거 할 게 없으니 빈 상태로 두고 가기 */
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {


}
/*
* 다 하고, resources > data.sql 가서 user 정보 넣고
* TDD > JpaRepositoryTest 하기 */