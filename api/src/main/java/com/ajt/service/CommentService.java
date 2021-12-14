package com.ajt.service;

import com.ajt.domain.Comment;
import com.ajt.domain.Posts;
import com.ajt.dto.comment.CommentRequestDto;
import com.ajt.repository.CommentRepository;
import com.ajt.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class CommentService {

    private final PostsRepository postsRepository;

    private final CommentRepository commentRepository;

    //댓글 생성
    public Comment save (Long id,CommentRequestDto commentRequestDto){
        Posts posts = postsRepository.findById(id).get();

        Comment comment =null;
        comment.setPost(posts);

        commentRepository.save(comment);
        return comment;
    }
    //댓글 리스트 반환
    public List<Comment> getList(Long id){

        Posts posts = postsRepository.findById(id).orElseThrow();
        return commentRepository.findAllDesc();
    }
    //댓글 삭제
    public void deleteComment( Long id, Long commentID){
        Posts posts =postsRepository.findById(id).orElseThrow();

        commentRepository.deleteById(commentID);
    }
    //댓글 수정
    public Comment commentUpdate(Long id , Long commentID , CommentRequestDto commentRequestDto){
        Posts posts = postsRepository.findById(id).orElseThrow();
        commentRequestDto.setPosts(posts);

        Comment newComment = commentRepository.findById(commentID).orElseThrow();
        newComment.setContents(commentRequestDto.getContent());

        return newComment;
    }
}
