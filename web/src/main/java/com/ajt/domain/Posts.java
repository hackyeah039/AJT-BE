package com.ajt.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 최초 작성일 : 2021-12-09
 * 최초 작성자 : Jang
 *
 * 게시글 관련 DataBase Table Entity
 */


@NoArgsConstructor
@Getter
@Entity
// TimeEntity 상속 받아 생성일(createdAt) 최종 수정일(updatedAt) 추가
public class Posts extends TimeEntity {

    //게시글 번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Mysql Auto Increment
    private Long id;

    //게시글 제목
    @Column(nullable = false)
    private String title;

    //게시글 작성자
    @Column(nullable = false)
    private String writer;

    //게시글 내용
    // 컬럼 타입 TEXT 지정
    @Column(columnDefinition = "TEXT")
    private String content;


}
