package com.ajt.controller;

import com.ajt.dto.PostsResponseDto;
import com.ajt.repository.PostsRepository;
import com.ajt.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 최초 작성일 : 2021-12-09
 * 최초 작성자 : Jang
 *
 * Posts Api 와 관련한 요청을 처리하는 Controller
 */


@RequiredArgsConstructor
@RestController
public class PostsController {

    //DB에 데이터 처리 요청을 하는 Service
    private final PostsService postsService;

    // 해당하는 id의 게시글을 조회하여 데이터 응답
    @GetMapping("/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }

}
