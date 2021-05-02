package com.niedbalski.simpleRest.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/git")
public class GitController {

    @GetMapping(path = "/getUsers")
    public String getUsers() {
        return "Hello World!";
    }
}
