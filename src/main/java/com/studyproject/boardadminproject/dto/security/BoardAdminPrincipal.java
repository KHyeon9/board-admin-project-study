package com.studyproject.boardadminproject.dto.security;

import com.studyproject.boardadminproject.domain.constant.RoleType;
import com.studyproject.boardadminproject.dto.AdminAccountDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public record BoardAdminPrincipal(
        String username,
        String password,
        Collection<? extends GrantedAuthority> authorities,
        String email,
        String nickname,
        String memo,
        Map<String, Object> oAuth2Attributes
) implements UserDetails, OAuth2User {

    public static BoardAdminPrincipal of(String username, String password, Set<RoleType> roleTypes, String email, String nickname, String memo) {
        return BoardAdminPrincipal.of(username, password, roleTypes, email, nickname, memo, Map.of());
    }

    public static BoardAdminPrincipal of(String username, String password, Set<RoleType> roleTypes, String email, String nickname, String memo, Map<String, Object> oAuth2Attributes) {
        return new BoardAdminPrincipal(
                username,
                password,
                roleTypes.stream()
                        .map(RoleType::getRoleName)
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toUnmodifiableSet()),
                email,
                nickname,
                memo,
                oAuth2Attributes
        );
    }

    public static BoardAdminPrincipal from(AdminAccountDto dto) {
        return BoardAdminPrincipal.of(
            dto.userId(),
            dto.userPassword(),
            dto.roleTypes(),
            dto.email(),
            dto.nickname(),
            dto.memo()
        );
    }

    public AdminAccountDto toDto() {
        return AdminAccountDto.of(
          username,
          password,
          authorities.stream()
                  .map(GrantedAuthority::getAuthority)
                  .map(RoleType::valueOf)
                  .collect(Collectors.toUnmodifiableSet())
          ,
          email,
          nickname,
          memo
        );
    }

    @Override public String getUsername() { return username; }
    @Override public String getPassword() { return password; }

    @Override public Collection<? extends GrantedAuthority> getAuthorities() { return authorities; }

    // spring security에 어느정도 위임해서 true로 반환한다.
    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }

    // oauth관련
    @Override public Map<String, Object> getAttributes() { return oAuth2Attributes; }

    @Override public String getName() { return username; }
}
