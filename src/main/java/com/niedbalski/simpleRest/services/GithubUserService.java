package com.niedbalski.simpleRest.services;

import com.niedbalski.simpleRest.dtos.GithubUser;
import com.niedbalski.simpleRest.dtos.UserDto;
import com.niedbalski.simpleRest.entities.UserEntity;
import com.niedbalski.simpleRest.repositories.GithubUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

@Service
@Slf4j
public class GithubUserService {

    @Value("${githubUserUrl}")
    private String githubUserUrl;

    private final RestTemplate restTemplate;

    private final GithubUserRepository githubUserRepository;

    @Autowired
    public GithubUserService(RestTemplate restTemplate, GithubUserRepository githubUserRepository) {
        this.restTemplate = restTemplate;
        this.githubUserRepository = githubUserRepository;
    }

    public UserDto getUserInfo(String login) {
        GithubUser githubUser = restTemplate.getForObject(githubUserUrl + login, GithubUser.class);
        log.debug("Get user info from github api invoked: " + githubUser);
        UserDto userDto = mapGithubUserToUser(githubUser);
        saveApiInvocationForLogin(login);
        return userDto;
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
        log.debug("Make calculation invoked for followers: {} and publicRepos: {}", followers, publicRepos);
        BigDecimal result = BigDecimal.valueOf(0);
        if (followers < 1) {
            log.info("Number of followers < 1. Can not make calculations. 0 will be returned!");
        } else {
            result = BigDecimal.valueOf(6).divide(BigDecimal.valueOf(followers), 20, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(2).add(BigDecimal.valueOf(publicRepos)));
        }
        return result.toString();
    }

    private UserEntity saveApiInvocationForLogin(String login) {
        Optional<UserEntity> userEntity = githubUserRepository.findByLogin(login);
        log.debug("User found by login {}: {}", login, userEntity);
        UserEntity entity = userEntity.orElse(new UserEntity(login, 0L));
        entity.setRequestCount(entity.getRequestCount() + 1L);
        UserEntity savedEntity = githubUserRepository.save(entity);
        log.debug("User saved to db. {}", savedEntity);
        return savedEntity;
    }
}
