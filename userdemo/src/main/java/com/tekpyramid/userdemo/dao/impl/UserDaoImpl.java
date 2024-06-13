package com.tekpyramid.userdemo.dao.impl;

import com.tekpyramid.userdemo.dao.UserDao;
import com.tekpyramid.userdemo.data.models.entity.User;
import com.tekpyramid.userdemo.repository.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Data
public class UserDaoImpl implements UserDao {

    private final UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }

    public List<User> findByName(String name){
        return userRepository.findByName(name);
    }

    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public Optional<User> findByPhoneAndPassword(long phone, String password) {
        return userRepository.findByMobileNumberAndPassword(phone, password);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public boolean deleteById(String id) {
        Optional<User> recUser = findById(id);
        if (recUser.isPresent()) {
            userRepository.delete(recUser.get());
            return true;
        }
        return false;
    }

}
