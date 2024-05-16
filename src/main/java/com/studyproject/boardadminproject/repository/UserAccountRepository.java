package com.studyproject.boardadminproject.repository;

import com.studyproject.boardadminproject.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, String> {

}
