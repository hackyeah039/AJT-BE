package com.ajt.service.post;

import com.ajt.domain.Posts;
import com.ajt.dto.PostsRequestDto;
import com.ajt.dto.PostsResponseDto;
import com.ajt.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
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

    //수정, 에러뜨면 rollback위해 트랜잭션
    @Transactional
    public Long update(final Long id, final PostsRequestDto modified) throws Exception {

        Posts post = repository.findById(id).orElseThrow(()->new Exception("수정 findById중에 에러"));
        post.update(modified.getTitle(), modified.getContent());
        return id;
    }

    //생성 , dto에서 toEntity()이용해서 저장
    @Transactional
    public Long create(final PostsRequestDto dto){
        Posts post = repository.save(dto.toEntity());
        return post.getId();
    }


}
