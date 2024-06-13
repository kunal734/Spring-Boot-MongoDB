package com.tekpyramid.userdemo.service.impl;

import com.tekpyramid.userdemo.dao.UserDao;
import com.tekpyramid.userdemo.data.models.dto.ApiResponse;
import com.tekpyramid.userdemo.data.models.dto.UserDto;
import com.tekpyramid.userdemo.data.models.entity.User;
import com.tekpyramid.userdemo.exceptionhandlers.UserNotFoundException;
import com.tekpyramid.userdemo.service.UserService;
import com.tekpyramid.userdemo.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final ModelMapper modelMapper;
    private final EmailServiceImpl emailServiceImpl;
    private String format = "Hi %s";

    public ResponseEntity<ApiResponse> saveUser(UserDto userDto) {

        User user = modelMapper.map(userDto, User.class);
        user.setCreatedOn(LocalDateTime.now().toString());



        String subject = String.format(format, user.getName());
        emailServiceImpl.sendEmail(userDto.getEmail(),subject,"Your Account has been created...");
        return ResponseUtil.createResponse(userDao.saveUser(user));
    }

    public ResponseEntity<ApiResponse> updateUser(UserDto userDto,String id) {
        User user=userDao.findById(id).orElseThrow(()->new NoSuchElementException("Id not found"));
        modelMapper.map(userDto,user);
        return ResponseUtil.updateResponse(userDao.saveUser(user));
    }

    public ResponseEntity<ApiResponse> findById(String id){
        return userDao.findById(id).map(ResponseUtil::createOkResponse).orElseThrow(()-> new NoSuchElementException("Id Not Found"));
    }

    public ResponseEntity<ApiResponse> findByName(String name){
        List<User> users=userDao.findByName(name);
        return Optional.of(users).filter(list -> !list.isEmpty()).map(ResponseUtil::createOkResponse).orElseThrow(() -> new UserNotFoundException("User Name Not Found"));
    }

    public ResponseEntity<ApiResponse> findByEmail(String email){
        return userDao.findByEmail(email).map(ResponseUtil::createOkResponse).orElseThrow(()-> new UserNotFoundException("Incorrect User Email!!!"));
    }

    public ResponseEntity<ApiResponse> findByEmailAndPassword(String email, String password){
        return userDao.findByEmailAndPassword(email, password).map(ResponseUtil::createOkResponse).orElseThrow(()-> new UserNotFoundException("Incorrect User Email or Password!!!"));
    }

    public ResponseEntity<ApiResponse> findByPhoneAndPassword(long phone, String password){
//        Optional<User> optionalUser=userDao.findByPhoneAndPassword(phone, password);
//        if(optionalUser.isPresent())
//            return ResponseUtil.createOkResponse(userDao.findByPhoneAndPassword(phone, password));
//        else
//            throw new UserNotFoundException("Incorrect User Phone or Password");
        return userDao.findByPhoneAndPassword(phone, password).map(ResponseUtil::createOkResponse).orElseThrow(() -> new UserNotFoundException("Incorrect User Phone or Password"));
    }

    public ResponseEntity<ApiResponse> findAll(int pageNo, int size) {
        List<User> users=userDao.findAll(PageRequest.of(pageNo,size));
        return Optional.of(users).filter(list->!list.isEmpty()).map(ResponseUtil::createOkResponse).orElseThrow(()-> new UserNotFoundException("No Users Found!!!"));
    }

    public ResponseEntity<ApiResponse> deleteById(String id) {
//        Optional<User> optionalUser=userDao.findById(id);
//        if(optionalUser.isPresent()) {
//            return ResponseUtil.createOkResponse(userDao.deleteById(id));
//        }
//        else {
//            throw new UserNotFoundException("User Id Not Found");
//        }
        return userDao.findById(id).map(user -> ResponseUtil.createOkResponse(userDao.deleteById(id))).orElseThrow(() -> new UserNotFoundException("User Id Not Found"));
    }
}
