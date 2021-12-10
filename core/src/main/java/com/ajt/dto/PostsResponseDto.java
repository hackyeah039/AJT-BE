package com.ajt.dto;

import com.ajt.domain.Posts;
import com.ajt.domain.TimeEntity;
import com.ajt.repository.PostsRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

/**
 * 최초 작성일 : 2021-12-09
 * 최초 작성자 : Jang
 *
 * Client 에서 요청한 정보를 응답할때 사용하는 DTO 클래스
 */

@Getter
public class PostsResponseDto extends TimeEntity {

    // 게시글 번호
    private Long id;

    //게시글 제목
    private String title;

    //게시글 작성자
    private String writer;

    //게시글 내용
    private String content;

    //조회 수
    private int hits;

    //삭제 여부
    private char deleteYn;

    //생성일, 시간
    private LocalDateTime CreatedAt;

    //수정일, 시간
    private LocalDateTime updatedAt;

    // DB에서 조회한 Posts 객체를 이용하여 응답용 DTO 생성 하는 생성자
    public PostsResponseDto(Posts post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.writer = post.getWriter();
        this.content = post.getContent();
        this.hits=post.getHits();
        this.CreatedAt=post.getCreatedAt();
        this.updatedAt=post.getUpdatedAt();
    }
}
