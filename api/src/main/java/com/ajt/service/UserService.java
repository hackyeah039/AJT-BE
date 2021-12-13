package com.ajt.service;

import com.ajt.domain.User;
import com.ajt.dto.user.UserSaveRequestDto;
import com.ajt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

//    public int save(UserSaveRequestDto dto){
//        User user =userRepository.save(dto.toEntity());
//        return user.getId();
//    }
}
