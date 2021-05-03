package com.niedbalski.simpleRest.controllers;

import com.niedbalski.simpleRest.dtos.UserDto;
import com.niedbalski.simpleRest.services.GithubUserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiOperation(value = "View github user info with calculations", response = UserDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved github user info"),
            @ApiResponse(code = 400, message = "The login you were trying to reach is not found"),
            @ApiResponse(code = 404, message = "Other client error"),
            @ApiResponse(code = 500, message = "Server error")
    }
    )
    @GetMapping(path = "/{login}")
    public ResponseEntity<UserDto> getGitUserInfo(@PathVariable String login) {
        log.debug("Get github user info invoked for login: {}", login);
        UserDto userInfo = githubUserService.getUserInfo(login);
        log.debug("Get user info response: {}", userInfo);
        return ResponseEntity.ok(userInfo);
    }
}
