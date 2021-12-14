package com.ajt.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Comment extends TimeEntity{

    @Id
    @GeneratedValue
    @Column(name = "COMMENT_ID")
    private Long id;

    private String contents;

    private String writer;

    //한 포스트 안에 여러개의 코맨트
    @ManyToOne
    @JoinColumn(name = "POST_ID")
    private Posts post;


}
