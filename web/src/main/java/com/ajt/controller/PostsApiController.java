package com.ajt.controller;

import com.ajt.domain.Posts;
import com.ajt.dto.PostsRequestDto;
import com.ajt.dto.PostsResponseDto;
import com.ajt.service.post.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class PostsApiController {

    private final PostsService postsService;

//    //게시글 리스트 조회
//    @GetMapping("/posts")
//    public List<PostsResponseDto> findAll() {
//        return postsService.findAll();
//    }

    //게시글 수정
    @PutMapping("/posts/{id}")
    public Long update(@PathVariable final Long id, @RequestBody final PostsRequestDto dto) throws Exception {
        return postsService.update( id, dto);
    }

    //게시글 생성
    @PostMapping("/posts")
    public Long create(@RequestBody final PostsRequestDto dto) {
        return postsService.create(dto);
    }

}
