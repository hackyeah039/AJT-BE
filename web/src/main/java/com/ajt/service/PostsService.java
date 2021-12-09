package com.ajt.service;

import com.ajt.domain.Posts;
import com.ajt.dto.PostsResponseDto;
import com.ajt.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository repository;

    // 인자로 받은 id의 게시글을 조회하여 응답 DTO로 반환하는 함수
    public PostsResponseDto findById(Long id){
        Posts post = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 id의 게시글이 존재하지 않습니다. id : " +id));
        return new PostsResponseDto(post);
    }

    //모든 게시글을 조회하여 데이터를 반환하는 함수
    public List<PostsResponseDto> findAll(){
        List<Posts> postsList =  repository.findAll();
        // List의 요소인 Posts 를 응답용 DTO인 PostsResponseDto로 변경하여 반환
        return postsList.stream().map(PostsResponseDto::new).collect(Collectors.toList());
    }


}
