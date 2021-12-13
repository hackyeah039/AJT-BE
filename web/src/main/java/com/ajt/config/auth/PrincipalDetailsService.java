package com.ajt.config.auth;

import com.ajt.domain.User;
import com.ajt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// 시큐리티 설정에서 processingUrl "/login"여기에서
// /login 요청이 오면 자동으로 UserDetailsService타입으로 ioc되어있는 loadByUsername 함수가 실행

@Service
public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    //시큐리티 session = Authentication =UserDetails
    //함수 종료시에 @AuthenticationPrincipal이 만들어짐
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user= userRepository.findByUsername(username);
        if(user != null){
            return new PrincipalDetails(user); //Authentication으로 들어가고
            //이후에 Session안으로 Authentication으로 들어감
            //Session(Authentication(UserDetails))
            // 결국 로그인 시 form에서 username으로 로그인 하면 그걸 받아서 ProcessingUrl 으로 가고 가면 IOC컨테이너 안에있는 이 함수가 자동 실행되고 이게 실행되면 Authentication이 만들어져서 할당되고 Authenticaion만들어지면 Session으로 자동할당되어서 로그인 완료
            //로그인 완료
        }
        return null;
    }
}
