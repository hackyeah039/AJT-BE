package com.ajt.dto;

import lombok.Getter;

/**
 * 최초 작성일 : 2021-12-09
 * 최초 작성자 : Jang
 *
 * Client 에서 게시글 수정 요청을 할때 수정 할 데이터를 받을 DTO 객체
 */

@Getter
public class PostsUpdateRequestDto {

    // 수정할 게시글 제목
    private String title;

    //수정할 게시글 내용
    private String content;

}
