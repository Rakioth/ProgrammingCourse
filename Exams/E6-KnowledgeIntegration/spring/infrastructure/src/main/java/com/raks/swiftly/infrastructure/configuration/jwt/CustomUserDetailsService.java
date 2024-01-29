package com.raks.swiftly.infrastructure.configuration.jwt;

import com.raks.swiftly.domain.exception.EntityNotFoundException;
import com.raks.swiftly.domain.model.enums.Role;
import com.raks.swiftly.infrastructure.model.entity.User;
import com.raks.swiftly.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository _userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = _userRepository.findByUsername(username)
                                   .orElseThrow(EntityNotFoundException::new);

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                getAuthorities(user.getRole())
        );
    }

    private List<SimpleGrantedAuthority> getAuthorities(Role role) {
        List<SimpleGrantedAuthority> authorities = role.getPermissions()
                                                       .stream()
                                                       .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                                                       .collect(Collectors.toList());

        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.name()));
        return authorities;
    }

}