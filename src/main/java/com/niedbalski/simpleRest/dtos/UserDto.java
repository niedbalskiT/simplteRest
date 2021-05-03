package com.niedbalski.simpleRest.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class UserDto {

    private String id;
    private String login;
    private String name;
    private String type;
    private String avatarUrl;
    private String createdAt;
    private String calculations;
}
