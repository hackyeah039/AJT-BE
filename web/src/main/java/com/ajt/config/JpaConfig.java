package com.ajt.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * 최초 작성일 : 2021-12-09
 * 최초 작성자 : Jang
 *
 * TimeEntity.class 에 사용된 JpaAuditing 을 사용하기 위한 @EnableJpaAuditing 설정 추가
 */


@EnableJpaAuditing
@Configuration
public class JpaConfig {
}
