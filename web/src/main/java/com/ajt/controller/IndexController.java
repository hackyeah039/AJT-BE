package com.ajt.controller;


import com.ajt.config.auth.PrincipalDetails;
import com.ajt.domain.User;
import com.ajt.dto.user.UserSaveRequestDto;
import com.ajt.repository.UserRepository;
import com.ajt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.Text;

import javax.websocket.Session;

/**
 * 최초 작성일 : 2021-12-08
 * 최초 작성자 : Addy
 *
 * 서버 정상 동작을 확인하기 위한 테스트 Controller
 */


@Controller
public class IndexController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/")
    public @ResponseBody String index(@AuthenticationPrincipal PrincipalDetails principalDetails){
        System.out.println("여기서 부터 인덱스");
        System.out.println(principalDetails.getAttributes());
        return principalDetails.getUsername();
    }
    @GetMapping("/a")
    public @ResponseBody String index2(@AuthenticationPrincipal PrincipalDetails principalDetails){
        System.out.println("여기서 부터 인덱스2");
        System.out.println(principalDetails.getUsername());
        System.out.println(principalDetails.getAttributes());
        System.out.println(principalDetails.getPassword());
        System.out.println(principalDetails.getAuthorities());
        return principalDetails.getUsername();
    }

    @GetMapping("/test/login")
    @ResponseBody
    public String loginTest(Authentication authentication,@AuthenticationPrincipal PrincipalDetails userDetails
    ) {
        System.out.println("컨트롤러 /test/login=====================");
        System.out.println("컨트롤러 authentication : "+ authentication.getPrincipal());
        PrincipalDetails principalDetails =(PrincipalDetails)authentication.getPrincipal();
        System.out.println("authentication : " +principalDetails.getUser());
//        System.out.println("세션 : " + session.getId() +"세션 : "+session.getClass()+","+session.getQueryString());
        System.out.println("userDetails : " +userDetails.getUser());
        System.out.println("userDetails : " +userDetails.getUsername());
        System.out.println("userDetails : " +userDetails.getAuthorities());
        System.out.println("userDetails : " +userDetails.getPassword());
        System.out.println("userDetails : " +userDetails.getClass());
        return "세션 정보 확인하기 ";
    }
    @GetMapping("/oauth/test/login")
    @ResponseBody
    public String oauthloginTest(Authentication authentication,@AuthenticationPrincipal PrincipalDetails userDetails
    ) {
        System.out.println("컨트롤러 /oauth/test/login===================== ");
        System.out.println("컨트롤러 /oauth/test/login authentication : "+ authentication.getPrincipal());
        OAuth2User principalDetails =(OAuth2User)authentication.getPrincipal();
        System.out.println("컨트롤러 /oauth/test/login PrincipalDetails : " +principalDetails.getAttributes());
//        System.out.println("세션 : " + session.getId() +"세션 : "+session.getClass()+","+session.getQueryString());
//        System.out.println("OAuth2 User Details :  getUser" +userDetails.getUser());
//        System.out.println("OAuth2 User Details : getAttributes" +userDetails.getAttributes());

        return "세션 정보 확인하기 ";
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/bye")
    public String bye(){
        return "bye";
    }

    //회원가입 url
    @PostMapping("/join")
    public String join ( User user){
        System.out.println(user);

        user.setRole("ROLE_USER");
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);
        userRepository.save(user);
        return "redirect:/loginForm";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/info")
    @ResponseBody
    public String info(){
        return "개인정보";
    }
    @GetMapping("/joinForm")
    public String joinForm() {
        return "joinForm";
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "loginForm";
    }

    @ResponseBody
    @GetMapping("/data")
    public String testData(){
        return "testData";
    }

    @ResponseBody
    @GetMapping("/user")
    public String testUrl1(@AuthenticationPrincipal PrincipalDetails principalDetails){
        System.out.println("/user 컨트롤러 principalDetails : " +principalDetails.getUser());
        return "user";
    }

    @ResponseBody
    @GetMapping("/admin")
    public String testUrl2(){return "admin";}

    @ResponseBody
    @GetMapping("/manager")
    public String testUrl3(){return "manager";}



}
