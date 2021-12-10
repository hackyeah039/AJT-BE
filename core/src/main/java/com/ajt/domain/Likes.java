package com.ajt.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 최초 작성일 : 2021-12-10
 *
 * 게시글의 좋아요를 누른 유저정보를 저장하는 Entity
 */

@Getter
@NoArgsConstructor
@Entity
public class Likes extends TimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "LIKE_ID")
    // 구분자
    Long id;

    //게시글 테이블 참조
    @ManyToOne
    @JoinColumn(name = "POSTS_ID", nullable = false)
    private Posts post;

    // 좋아요를 누른 유저의 이름
    private String username;


    // Like가 Post를 참조할 Eo Posts의 List<Likes>에 현재(this) Likes 객체 추가
    public void setPost(Posts post){
        if(!post.getLikeList().contains(this)){
            post.getLikeList().add(this);
        }
    }

    @Builder
    public Likes(Posts post, String username) {
        this.post = post;
        this.username = username;
    }
}
