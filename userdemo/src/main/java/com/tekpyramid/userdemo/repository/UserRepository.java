package com.tekpyramid.userdemo.repository;

import com.tekpyramid.userdemo.data.models.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User,String> {

    public List<User> findByName(String name);

    public Optional<User> findByEmail(String email);

    public Optional<User> findByEmailAndPassword(String email,String password);

    public Optional<User> findByMobileNumberAndPassword(long phone, String password);
}
