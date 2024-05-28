package com.studyproject.boardadminproject.controller;

import com.studyproject.boardadminproject.config.SecurityConfig;
import com.studyproject.boardadminproject.config.TestSecurityConfig;
import com.studyproject.boardadminproject.domain.constant.RoleType;
import com.studyproject.boardadminproject.dto.AdminAccountDto;
import com.studyproject.boardadminproject.service.AdminAccountService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.BDDMockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@DisplayName("컨트롤러 - 어드민 회원 관리")
@Import(SecurityConfig.class)
@WebMvcTest(AdminAccountController.class)
class AdminAccountControllerTest {

    private final MockMvc mvc;

    // TestSecurityConfig의 목빈과 충돌하여 다시 아래에 securitySetup를 다시 선언
    @MockBean private AdminAccountService adminAccountService;
    
    public AdminAccountControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }
    
    @BeforeTestMethod
    public void securitySetup() {
        given(adminAccountService.searchUser(anyString()))
                .willReturn(Optional.of(createAdminAccountDto()));

        given(adminAccountService.saveUser(anyString(), anyString(), anySet(), anyString(), anyString(), anyString()))
                .willReturn(createAdminAccountDto());
    }

    @WithMockUser(username = "tester", roles = "USER")
    @DisplayName("[View][GET] 어드민 회원 페이지 - 정상 호출")
    @Test
    void givenAuthorizedUser_whenRequestingAdminMembersView_thenReturnsAdminMembersView() throws Exception {
        // Given

        // When&Then
        mvc.perform(get("/admin/members"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("admin/members"));
    }

    @WithMockUser(username = "tester", roles = "USER")
    @DisplayName("[Data][GET] 어드민 회원 리스트 - 정상 호출")
    @Test
    void givenAuthorizedUser_whenRequestingAdminMembers_thenReturnsAdminMembers() throws Exception {
        // Given
        given(adminAccountService.users()).willReturn(List.of());

        // When&Then
        mvc.perform(get("/api/admin/members"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        then(adminAccountService).should().users();
    }

    @WithMockUser(username = "tester", roles = "MANAGER")
    @DisplayName("[Data][DELETE] 어드민 회원 삭제 - 정상 호출")
    @Test
    void givenAuthorizedUser_whenDeletingAdminMembers_thenDeletesAdminMembers() throws Exception {
        // Given
        String userId = "hyeon";
        willDoNothing().given(adminAccountService).deleteUser(userId);

        // When&Then
        mvc.perform(
                delete("/api/admin/members/" + userId)
                        .with(csrf())
                )
                .andExpect(status().isNoContent());

        then(adminAccountService).should().deleteUser(userId);
    }

    private AdminAccountDto createAdminAccountDto() {
        return AdminAccountDto.of(
                "hyeonTest",
                "pw",
                Set.of(RoleType.USER),
                "hyeon-test@email.com",
                "hyeon-test",
                "test memo"
        );
    }
}