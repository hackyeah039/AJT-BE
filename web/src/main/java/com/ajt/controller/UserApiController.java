package com.ajt.controller;

import com.ajt.config.auth.PrincipalDetails;
import com.ajt.domain.User;
import com.ajt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    @Autowired
    UserRepository userRepository;
    //유저 데이터
    @GetMapping("/user/{id}")
    public User userInfo(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable Integer id){
        User user=userRepository.findById(id).orElseThrow();
        return user;
    }
}
