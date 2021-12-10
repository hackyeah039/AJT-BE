package com.ajt.repository;

import com.ajt.domain.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikesRepository extends JpaRepository<Likes, Long> {
}
