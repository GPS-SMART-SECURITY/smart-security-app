package com.isec.gps41.SmartSecurity.service;

import com.isec.gps41.SmartSecurity.exception.ResourcesInvalid;
import com.isec.gps41.SmartSecurity.model.User;
import com.isec.gps41.SmartSecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User create(User u){
        try {
            if (userRepository.existsByEmail(u.getEmail())) {
                throw new ResourcesInvalid("Email used", HttpStatus.BAD_REQUEST);
            }
            return userRepository.save(u);
        }catch (DataAccessException ex){
            throw new ResourcesInvalid( ex.getMessage() , HttpStatus.BAD_REQUEST);
        }
    }

    public User findByEmail(String email, boolean active) {
        try {
            return userRepository.findByEmailAndActive(email, active);
        }catch (Exception e){
            throw new ResourcesInvalid("Invalid information", HttpStatus.NOT_FOUND);
        }
    }


}
