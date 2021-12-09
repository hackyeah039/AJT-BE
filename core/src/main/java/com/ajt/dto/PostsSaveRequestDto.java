package com.ajt.dto;

import lombok.Builder;
import lombok.Getter;

/**
 * 최초 작성일 : 2021-12-09
 * 최초 작성자 : Jang
 *
 * Client 에서 게시글 등록 요청을 할 때 등록할 데이터를 받을 DTO 객체
 */


@Getter
public class PostsSaveRequestDto {

    // 등록될 후 부여받을 게시글 번호
    private Long id;

    //등록할 게시글 제목
    private String title;

    //등록할 게시글 작성자
    private String writer;

    //등록할 게시글 내용
    private String content;

    @Builder
    public PostsSaveRequestDto(String title, String writer, String content) {
        this.title = title;
        this.writer = writer;
        this.content = content;
    }
}
