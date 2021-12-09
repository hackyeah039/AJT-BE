package com.ajt.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 최초 작성일 : 2021-12-08
 * 최초 작성자 : Addy
 *
 * 서버 정상 동작을 확인하기 위한 테스트 Controller
 */


@Controller
public class IndexController {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/bye")
    public String bye(){
        return "bye";
    }


    @ResponseBody
    @GetMapping("/data")
    public String testData(){
        return "testData";
    }

}
