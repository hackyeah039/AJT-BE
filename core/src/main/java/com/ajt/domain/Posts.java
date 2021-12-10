package com.ajt.domain;

import com.ajt.repository.PostsRepository;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 최초 작성일 : 2021-12-09
 * 최초 작성자 : Jang
 *
 * 게시글 관련 DataBase Table Entity
 */


@NoArgsConstructor
@Getter
@Entity
@Table(name = "Posts")
public class Posts extends TimeEntity {

    //게시글 번호
    @Id
    @GeneratedValue
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

    //조회수
    @Column(nullable = false)
    private int hits;


    //빌더패턴, 순서 상관없이 생성가능
    @Builder
    public Posts(String title,String writer, String content, int hits){
        this.title=title;
        this.writer=writer;
        this.content=content;
        this.hits=hits;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
