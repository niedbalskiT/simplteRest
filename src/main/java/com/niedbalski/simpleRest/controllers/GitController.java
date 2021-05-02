package com.niedbalski.simpleRest.controllers;

import com.niedbalski.simpleRest.dtos.GithubUser;
import com.niedbalski.simpleRest.dtos.UserDto;
import com.niedbalski.simpleRest.services.GithubUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(path = "/git")
public class GitController {

    private GithubUserService gihubUserService;

    @Autowired
    public GitController(GithubUserService gihubUserService) {
        this.gihubUserService = gihubUserService;
    }

    @GetMapping(path = "/users/{login}")
    @ResponseBody()
    public UserDto getGitUserInfo(@PathVariable String login) {
        return gihubUserService.getUserInfo(login);
    }
}
