package com.example.test;


import com.ajt.domain.Posts;
import com.ajt.repository.PostsRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@SpringBootConfiguration
@Rollback
public class BoardTests {

    @Autowired
    PostsRepository postsRepository;

    @Test
    @DisplayName("save 테스트")
    void save(){
        Posts post= Posts.builder()
                .title("테스트용 타이틀")
                .content("테스트용 컨텐츠")
                .author("테스트용 저자")
                .hits(0)
                .build();

        postsRepository.save(post);

        Posts showing = postsRepository.findById((long)1).get();
//        System.out.println(showing.getContent() +" 컨텐트");
//        System.out.println(showing.getDeleteYn() +" 삭제");
//        System.out.println(showing.getHits() +" 조회수");
//        System.out.println(showing.getWriter() +" 저자");
                
    }

    @Test
    @DisplayName("findALl 테스트")
    void findAll(){
        long count=postsRepository.count();

        List<Posts> post=postsRepository.findAll();
    }

    @Test
    @DisplayName("delete 테스트")
    void delete(){
        Posts post = postsRepository.findById((long)1).get();

        postsRepository.delete(post);
    }

}
