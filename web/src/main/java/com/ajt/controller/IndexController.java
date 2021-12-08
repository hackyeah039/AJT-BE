package com.ajt.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
