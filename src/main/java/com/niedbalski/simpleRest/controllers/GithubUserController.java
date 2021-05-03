package com.niedbalski.simpleRest.controllers;

import com.niedbalski.simpleRest.dtos.UserDto;
import com.niedbalski.simpleRest.services.GithubUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(path = "/users")
public class GithubUserController {

    private final GithubUserService githubUserService;

    @Autowired
    public GithubUserController(GithubUserService githubUserService) {
        this.githubUserService = githubUserService;
    }

    @GetMapping(path = "/{login}")
    public ResponseEntity<UserDto> getGitUserInfo(@PathVariable String login) {
        log.debug("Get github user info invoked for login: {}", login);
        UserDto userInfo = githubUserService.getUserInfo(login);
        log.debug("Get user info response: {}", userInfo);
        return ResponseEntity.ok(userInfo);
    }
}
