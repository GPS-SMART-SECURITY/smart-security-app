package com.isec.gps41.SmartSecurity.service;

import com.isec.gps41.SmartSecurity.exception.InvalidToken;
import com.isec.gps41.SmartSecurity.exception.ParamInvalid;
import com.isec.gps41.SmartSecurity.model.Division;
import com.isec.gps41.SmartSecurity.model.Floor;
import com.isec.gps41.SmartSecurity.model.User;
import com.isec.gps41.SmartSecurity.payload.BuildingDetailsRequest;
import com.isec.gps41.SmartSecurity.payload.UserDto;
import com.isec.gps41.SmartSecurity.payload.floor.FloorDto;
import com.isec.gps41.SmartSecurity.payload.users.UserNewRequest;
import com.isec.gps41.SmartSecurity.payload.users.UsersList;
import com.isec.gps41.SmartSecurity.repository.FloorRepository;
import com.isec.gps41.SmartSecurity.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class BuildingService {

    @Autowired
    UserService userService;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    FloorRepository floorRepository;

    @Autowired
    AuthService authService;

    public UsersList getUsers(int numPage, int size, String ord) {
        return null;
    }




    public UserDto newUser(UserNewRequest userNewRequest, String token) {
        return null;
    }


    public UserDto updateUser(UserNewRequest userUpdateRequest, UUID uuid) {
       return null;
    }



    public BuildingDetailsRequest getBuildingDetails() {
        return null;
    }

    public void destroyUser(UUID uuid) {

    }

    public UserDto getUserByUUID(UUID uuid) {
        return null;
    }

    public UserDto getUserDetails(String token) {
        return null;
    }

    public void inativeUser(UUID uuid) {
    }
}
