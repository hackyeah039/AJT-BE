package com.ajt.service;

import com.ajt.domain.Likes;
import com.ajt.domain.Posts;
import com.ajt.dto.likes.LikesRequestDto;
import com.ajt.repository.LikesRepository;
import com.ajt.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * 최초 작성일 : 2021-12-10
 *
 * 게시글의 좋아요와 관련된 요청 처리 Service
 */

@Transactional
@RequiredArgsConstructor
@Service
public class LikesService {

    private final LikesRepository likesRepository;
    // Likes의 post 필드 매핑을 위한 Repository
    private final PostsRepository postsRepository;

    public Long save(LikesRequestDto dto) {

        // 좋아요 누른 게시글 정보 받아와 매핑
        Posts post = postsRepository.findById(dto.getPost_id())
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id : "+ dto.getPost_id()));

        Likes like = Likes.builder()
                .username(dto.getUsername())
                .post(post)
                .build();

        return likesRepository.save(like).getId();
    }
}
