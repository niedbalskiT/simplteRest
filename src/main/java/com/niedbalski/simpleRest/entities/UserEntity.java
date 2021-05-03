package com.niedbalski.simpleRest.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "github_users")
@NoArgsConstructor
@ToString
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "login", unique = true)
    private String login;

    @Column(name = "request_count")
    private Long requestCount;

    public UserEntity(String login, Long requestCount) {
        this.login = login;
        this.requestCount = requestCount;
    }
}

