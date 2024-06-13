package com.fireflink.feature.dao;

import com.fireflink.feature.models.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    User saveUser(User user);

    Page<User> findAllUsers(int pageNo, int pageSize);

    Page<User> findAllActiveUsers(int pageNo, int pageSize);

    Optional<User> findById(String id);

    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndPassword(String email, String password);

    List<User> searchUsersToAssign(String name, int pageNo, int pageSize);

    String deleteUserByEmail(String email);

    List<User> findByName(String name);

    List<User> findByTeam(String team);

//    User findByPhone(long phone);

    Optional<User> setPassword(String email, String password);
}
