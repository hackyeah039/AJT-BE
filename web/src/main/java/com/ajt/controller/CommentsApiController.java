package com.ajt.controller;

import com.ajt.config.auth.PrincipalDetails;
import com.ajt.domain.Comment;
import com.ajt.dto.comment.CommentRequestDto;
import com.ajt.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentsApiController {

    private final CommentService commentService;

    //댓글 생성
    @PutMapping("/posts/{id}/comment")
    public Comment save(@PathVariable Long id, @RequestBody CommentRequestDto commentRequestDto , @AuthenticationPrincipal PrincipalDetails principalDetails) {
        commentRequestDto.setWriter(principalDetails.getUsername());
        return commentService.save(id,commentRequestDto);
    }

    //댓글 리스트 반환
    @GetMapping("/posts/{id}/comment")
    public List<Comment> getList(@PathVariable Long id){
        return getList(id);
    }

    //댓글 삭제
    @DeleteMapping("/posts/{id}/comment/{commentID}")
    public void deleteComment(@PathVariable Long id, @PathVariable Long commentID){
        deleteComment(id,commentID);
    }

    //댓글 수정
    @PostMapping("/posts/{id}/comment/{commentID}")
    public Comment commentUpdate(@PathVariable Long id , @PathVariable Long commentID , @RequestBody CommentRequestDto commentRequestDto){
        return commentUpdate(id,commentID,commentRequestDto);
    }

}
