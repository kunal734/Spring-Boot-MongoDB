package com.fireflink.feature.dao.impl;

import com.fireflink.feature.dao.UserDao;
import com.fireflink.feature.models.entity.User;
import com.fireflink.feature.repository.UserRepository;
import com.fireflink.feature.util.AccountStatus;
import com.fireflink.feature.util.Role;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Data
public class UserDaoImpl implements UserDao {

    private final UserRepository userRepository;
    private final MongoTemplate mongoTemplate;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Page<User> findAllUsers(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return userRepository.findAll(pageable);
    }

    @Override
    public Page<User> findAllActiveUsers(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return userRepository.findAllActiveUsers(pageable);
    }

    @Override
    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
       return Optional.ofNullable(userRepository.findByEmail(email)).orElseThrow(() -> new NoSuchElementException("Invalid Email"));
    }

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public List<User> searchUsersToAssign(String name, int pageNo, int pageSize) {
        Query query = new Query(Criteria.where("role").nin(Role.Admin, Role.Customer).andOperator(Criteria.where("name").is(name)));
        return mongoTemplate.find(query.with(PageRequest.of(pageNo,pageSize)), User.class);
    }

    @Override
    public String deleteUserByEmail(String email) {
        Optional<User> user= userRepository.findByEmail(email);
        if(user.isPresent()){
            userRepository.deleteById(user.get().getId());
            return "User Deleted Successfully";
        }
        return "User Not Found";
    }

    @Override
    public List<User> findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public List<User> findByTeam(String team) {
        return userRepository.findByTeam(team);
    }

//    @Override
//    public User findByPhone(long phone) {
//        Query query = Query.query(Criteria.where("phone").is(phone));
//        return mongoTemplate.findOne(query,User.class);
//
//    }

    @Override
    public Optional<User> setPassword(String email, String password) {
        Optional<User> recUser=userRepository.findByEmail(email);
        if(recUser.isPresent()){
            User user=recUser.get();
            user.setPassword(password);
            user.setStatus(AccountStatus.ACTIVE);
            user.setModifiedEntity(user.getName(), user.getEmail());
            return Optional.of(userRepository.save(user));
        }
        throw new NoSuchElementException("Please enter valid Email");
    }


}
