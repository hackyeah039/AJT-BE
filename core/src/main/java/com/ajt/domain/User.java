package com.ajt.domain;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@Data
@Entity
@Table(name = "Ajt_User") //user 충돌 방지
public class User extends TimeEntity{

    @Id
    @GeneratedValue
    @Column(name = "USER_ID")
    private int id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    private String role;//User, Admin

    @Column
    private String provider;

    @Column
    private String providerId;

    @Builder
    public User(String username, String password, String email, String role, String provider, String providerId) {

        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.provider = provider;
        this.providerId = providerId;
    }
}
