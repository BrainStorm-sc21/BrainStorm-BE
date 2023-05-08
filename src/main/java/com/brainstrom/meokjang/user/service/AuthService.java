package com.brainstrom.meokjang.user.service;

import com.brainstrom.meokjang.user.domain.User;
import com.brainstrom.meokjang.user.dto.request.LoginRequest;
import com.brainstrom.meokjang.user.dto.request.SignupRequest;
import com.brainstrom.meokjang.user.dto.response.AuthResponse;
import com.brainstrom.meokjang.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

    @Autowired
    private final UserRepository userRepository;

    public AuthResponse join(SignupRequest req) {
        try {
            User user = req.toEntity();
            validateDuplicateUser(user);
            userRepository.save(user);
            return new AuthResponse(user.getUserId());
        } catch (IllegalStateException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    public AuthResponse login(LoginRequest dto) {
        User user = userRepository.findByPhoneNumber(dto.getPhoneNumber())
                .orElseGet(() -> userRepository.findBySns(dto.getSnsType(), dto.getSnsKey())
                        .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다.")));
        return new AuthResponse(user.getUserId());
    }

    public void validateDuplicateUser(User user) {
        userRepository.findByName(user.getUserName())
                        .ifPresent(m -> {
                            throw new IllegalStateException("이미 존재하는 회원입니다.");
                        });
    }
}