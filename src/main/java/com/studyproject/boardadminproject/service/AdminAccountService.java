package com.studyproject.boardadminproject.service;

import com.studyproject.boardadminproject.domain.AdminAccount;
import com.studyproject.boardadminproject.domain.constant.RoleType;
import com.studyproject.boardadminproject.dto.AdminAccountDto;
import com.studyproject.boardadminproject.repository.AdminAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class AdminAccountService {

    private final AdminAccountRepository adminAccountRepository;

    @Transactional(readOnly = true)
    public Optional<AdminAccountDto> searchUser(String userId) {
        return adminAccountRepository.findById(userId).map(AdminAccountDto::from);
    }

    public AdminAccountDto saveUser(String userId, String password, Set<RoleType> roleTypes, String email, String nickname, String memo) {
        return AdminAccountDto.from(
                adminAccountRepository.save(
                        AdminAccount.of(userId, password, roleTypes, email, nickname, memo)
                )
        );
    }

    @Transactional(readOnly = true)
    public List<AdminAccountDto> users() {
        return adminAccountRepository
                .findAll()
                .stream()
                .map(AdminAccountDto::from)
                .toList();
    }

    public void deleteUser(String userId) {
        adminAccountRepository.deleteById(userId);
    }
}
