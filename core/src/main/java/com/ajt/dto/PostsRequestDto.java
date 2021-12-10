package com.ajt.dto;

import com.ajt.domain.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 최초 작성일 : 2021-12-09
 * 최초 작성자 : Jang
 * 마지막 수정 : 2021-12-09
 * 마지막 수정자: Addy
 *
 * Client 에서 게시글 등록,수정 요청을 할 때 등록할 데이터를 받을 DTO 객체
 */

@NoArgsConstructor
@Getter
public class PostsRequestDto {

    // 등록될 후 부여받을 게시글 번호
    private Long id;

    //등록할 게시글 제목
    private String title;

    //등록할 게시글 작성자
    private String writer;

    //등록할 게시글 내용
    private String content;


    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .content(content)
                .writer(writer)
                .hits(0)
                .build();
    }
}
