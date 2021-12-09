package com.ajt.repository;

import com.ajt.domain.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 최초 작성일 : 2021-12-09
 * 최초 작성자 : Jang
 *
 * Posts Database Table 에 접근하여 CRUD 작업을 처리하는 Repository
 */

public interface PostsRepository extends JpaRepository<Posts, Long> {

}
