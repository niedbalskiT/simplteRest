package com.niedbalski.simpleRest.repositories;

import com.niedbalski.simpleRest.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GitRepository extends JpaRepository<UserEntity, Long> {

    public Optional<UserEntity> findByLogin(String login);
}
