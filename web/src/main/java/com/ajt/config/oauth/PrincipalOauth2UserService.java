package com.ajt.config.oauth;

import com.ajt.config.auth.PrincipalDetails;
import com.ajt.config.oauth.oauth.GoogleUserInfo;
import com.ajt.config.oauth.oauth.NaverUserInfo;
import com.ajt.config.oauth.oauth.OAuth2UserInfo;
import com.ajt.domain.User;
import com.ajt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    //구글로 부터 받은 userRequest 데이터에대한 후처리 함수
    //함수 종료시에 @AuthenticationPrincipal이 만들어짐
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        //강제 회원가입.
        OAuth2User oAuth2User = super.loadUser(userRequest);

        OAuth2UserInfo oAuth2UserInfo =null;

        if(userRequest.getClientRegistration().getRegistrationId().equals("google")){
            System.out.println("구글 로그인 요청");
            oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
        }else if(userRequest.getClientRegistration().getRegistrationId().equals("naver")){
            System.out.println("네이버 로그인 요청");
//            System.out.println("네이버 attribute "+oAuth2User.getAttributes());

            oAuth2UserInfo = new NaverUserInfo((Map) oAuth2User.getAttributes().get("response"));
        }else{
            System.out.println("RegistrationId 범위를 벗어난 회원가입 요청입니다.");
        }


        String provider =oAuth2UserInfo.getProvider(); //'google'
        String username = oAuth2UserInfo.getProviderId(); // 구글 pk
        String email =oAuth2UserInfo.getEmail();
        String providerID =provider+"_"+username; //google_pk
        String password = bCryptPasswordEncoder.encode(UUID.randomUUID().toString()); //암호화 비밀번호
        String role ="ROLE_USER";

        User user = userRepository.findByUsername(username); //아이디 찾기

        if( user == null) {
            user = user.builder().
                    username(username)
                    .email(email)
                    .providerId(providerID)
                    .provider(provider)
                    .role(role)
                    .password(password)
                    .build();
            userRepository.save(user); //없는 경우 강제 회원가입
            System.out.println("처음 회원가입 하셨습니다.");
        }else{
            System.out.println("이미 회원가입한 아이디가 있습니다.");
        }
        return new PrincipalDetails(user , oAuth2User.getAttributes());
    }
}
