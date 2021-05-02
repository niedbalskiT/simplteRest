package com.niedbalski.simpleRest.dtos;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class GithubUser {

    private String login;
    private String id;
    private String avatar_url;
    private String type;
    private String name;
    private Integer public_repos;
    private Integer followers;
    private String created_at;
}
