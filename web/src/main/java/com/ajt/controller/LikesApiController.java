package com.ajt.controller;

import com.ajt.dto.likes.LikesRequestDto;
import com.ajt.service.LikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 최초 작성일 : 2021-12-10
 *
 * 게시글의 좋아요와 관련한 요청을 처리하는 Controller
 */

@RequiredArgsConstructor
@RestController
public class LikesApiController {

    private final LikesService service;

    @PostMapping("/likes")
    public Long save(@RequestBody LikesRequestDto dto){
        System.out.println(dto+ "--------------------------------------------------------");
        return service.save(dto);
    }

}
