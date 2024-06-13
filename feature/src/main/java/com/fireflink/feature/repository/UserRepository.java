package com.fireflink.feature.repository;

import com.fireflink.feature.models.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    List<User> findByName(String name);

    List<User> findByTeam(String team);

    @Query("{'status': 'ACTIVE'}")
    Page<User> findAllActiveUsers(Pageable pageable);

    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndPassword(String email, String password);

    List<User> findByStatus(String status);

    boolean existsByTeam(String team);
//    Optional<User> setPassword(String password);
}