package com.ajt.config;

import com.ajt.config.oauth.PrincipalOauth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity//스프링 시큐리티 필터
@EnableGlobalMethodSecurity(securedEnabled = true ,prePostEnabled = true) // 글로벌X 개인적 Secured, pre post Authorize 어노테이션 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PrincipalOauth2UserService principalOauth2UserService;

    @Override //시큐리티 설정 제외
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/console/**");
    }

    @Override //시큐리티 설정
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/user/**").authenticated()
                .antMatchers("/manager/**").access("hasRole('ROLE_ADMIN')  or hasRole('ROLE_MANAGER')")
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll()
                // 기존 로그인
                .and()
                    .formLogin()
                    .loginPage("/loginForm")
                    .loginProcessingUrl("/login") //  '/login'진행시 시큐리티가 낚아채서 로그인 진행
                    .defaultSuccessUrl("/") //로그인 완료시 redirect 페이지
                // OAuth2.0 로그인
                .and()
                    .oauth2Login()
                    .loginPage("/loginForm")
                // 로그인 이후 후처리
                    .userInfoEndpoint()
                    .userService(principalOauth2UserService);

    }
}
