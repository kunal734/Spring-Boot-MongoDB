package com.tekpyramid.userdemo.dao;

import com.tekpyramid.userdemo.data.models.entity.User;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface UserDao {

    public User saveUser(User user);

    public Optional<User> findById(String id);

    public List<User> findByName(String name);

    public Optional<User> findByEmail(String email);

    public Optional<User> findByEmailAndPassword(String email, String password);

    public Optional<User> findByPhoneAndPassword(long phone, String password);

    public List<User> findAll(Pageable pageable);

    public boolean deleteById(String id);
}
