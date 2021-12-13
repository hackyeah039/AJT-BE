package com.ajt.config.auth;
// 시큐리티가 /login 주소 요청이 오면 낚아채서 로그인 진생시킴.
// 로그인 진행이 완료되면 시큐리티가 session을 만들어줌. (Security ContextHolder) 라는 키값에 저장
// 오브젝트 => Authentication 타입 객체만 들어감
// Authentication 안에 User정보가 있어야 됨
// User오브젝트 타입 => UserDetails 타입 객체

import com.ajt.domain.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
//Security Session=> Authentication => UserDetails(PrincipalDetails)


// Security Session영역에 저장시에 들어갈수 있는 객체가 Authenticaion 객체만 됨
// Authenticaion 안에 User정보를 저장할때 유저정보는 UserDetails타입이여야 만 한다.

// 나중에 꺼내쓸때는 Security Session을 Get해서 꺼내올때 Authenticaion객체가 나옴
// 그리고 이 안에서 UserDetails꺼내면 User 객체에 접근 가능


// PrincipalDetails를 UserDetails로 상속받으면 PrincipalDetails(UserDetails 타입임 )
// 그럼 이 PrincipalDetails를 Authentication안에 넣을수 있음
//오버라이드 한다음에 우리 유저객체는 유저오브젝트가 담고 있으니깐 콤포지션을 입수
@Getter
public class PrincipalDetails implements UserDetails, OAuth2User {

    private User user;
    private Map<String, Object> attributes;

    // 일반 로그인시 사용 생성자
    public PrincipalDetails(User user) {
        this.user=user;
    }

    // OAuth 로그인 시에 사용되는 생성자
    public PrincipalDetails(User user,Map<String,Object> attributes) {
        this.user=user;
        this.attributes=attributes;
    }
    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public String getName() {
        return null;
    }

    //해당 User의 권한을 리턴하는 기능
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect =new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole();
            }
        });
        return collect;
    }
    //패스워드 리턴
    @Override
    public String getPassword() {
        return user.getPassword();
    }
    //유저네임 리턴

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() { return true; }
}
