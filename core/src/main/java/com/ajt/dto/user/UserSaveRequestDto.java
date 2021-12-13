package com.ajt.dto.user;

import com.ajt.domain.TimeEntity;
import com.ajt.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserSaveRequestDto extends TimeEntity {
    private int id;
    private String username;
    private String password;
    private String email;
    private String role;

//    public User toEntity(){
//        return User.builder()
////                .id(id)
//                .username(username)
//                .password(password)
//                .email(email)
////                .role(role)
//                .build();
//    }
}
