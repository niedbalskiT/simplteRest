package com.niedbalski.simpleRest.services;

import com.niedbalski.simpleRest.dtos.GithubUser;
import com.niedbalski.simpleRest.dtos.UserDto;
import com.niedbalski.simpleRest.entities.UserEntity;
import com.niedbalski.simpleRest.repositories.GitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

@Service
public class GithubUserService {

    @Value("${githubUserUrl}")
    private String githubUserUrl;

    private RestTemplate restTemplate;

    private GitRepository gitRepository;

    @Autowired
    public GithubUserService(RestTemplate restTemplate, GitRepository gitRepository) {
        this.restTemplate = restTemplate;
        this.gitRepository = gitRepository;
    }

    public UserDto getUserInfo(String login) {
        GithubUser githubUser = restTemplate.getForObject(githubUserUrl + login, GithubUser.class);
        return mapGithubUserToUser(githubUser);
    }

    private UserDto mapGithubUserToUser(GithubUser githubUser) {
        return new UserDto(
                githubUser.getId(),
                githubUser.getLogin(),
                githubUser.getName(),
                githubUser.getType(),
                githubUser.getAvatar_url(),
                githubUser.getCreated_at(),
                makeCalculation(githubUser.getFollowers(), githubUser.getPublic_repos()));
    }

    private String makeCalculation(Integer followers, Integer publicRepos) {

        BigDecimal result = BigDecimal.valueOf(6).divide(BigDecimal.valueOf(followers), 20, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(2).add(BigDecimal.valueOf(publicRepos)));
        return result.toString();
    }

    private UserEntity mapToUserEntity(String login) {

        return null;
    }
}
