package com.studyproject.boardadminproject.service;

import com.studyproject.boardadminproject.domain.constant.RoleType;
import com.studyproject.boardadminproject.dto.AdminAccountDto;
import com.studyproject.boardadminproject.repository.AdminAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class AdminAccountService {

    private final AdminAccountRepository adminAccountRepository;

    public Optional<AdminAccountDto> searchUser(String userId) {
        return Optional.empty();
    }

    public AdminAccountDto saveUser(String userId, String password, Set<RoleType> roleTypes, String email, String nickname, String memo) {
        return null;
    }

    public List<AdminAccountDto> users() {
        return List.of();
    }

    public void deleteUser(String userId) {

    }
}
