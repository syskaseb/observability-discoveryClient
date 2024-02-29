package com.example.blockingapi.auth.http.service.impl;

import com.example.blockingapi.auth.http.dto.LoginRequestDto;
import com.example.blockingapi.user.postgres.PostgresUser;
import com.example.blockingapi.user.postgres.PostgresUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final PostgresUserRepository userRepository;
    private final PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        PostgresUser user = userRepository.findByUsername(username);
        if (user != null) {
            return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    public PostgresUser save(LoginRequestDto user) {
        PostgresUser newUser = new PostgresUser(
                user.getUsername(),
                bcryptEncoder.encode(user.getPassword()));

        return userRepository.save(newUser);
    }
}