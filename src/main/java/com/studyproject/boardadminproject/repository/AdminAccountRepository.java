package com.studyproject.boardadminproject.repository;

import com.studyproject.boardadminproject.domain.AdminAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminAccountRepository extends JpaRepository<AdminAccount, String> {

}
