package com.isec.gps41.SmartSecurity.service;

import com.isec.gps41.SmartSecurity.constants.ROLES;
import com.isec.gps41.SmartSecurity.exception.ResourcesInvalid;
import com.isec.gps41.SmartSecurity.model.Division;
import com.isec.gps41.SmartSecurity.model.Register;
import com.isec.gps41.SmartSecurity.model.User;
import com.isec.gps41.SmartSecurity.payload.division.DivisionDto;
import com.isec.gps41.SmartSecurity.payload.log.RegisterDto;
import com.isec.gps41.SmartSecurity.payload.log.RegisterPageable;
import com.isec.gps41.SmartSecurity.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AlarmManagementService {

    @Autowired
    UserService userService;

    @Autowired
    JwtTokenProvider tokenProvider;



    public List<DivisionDto> desativateAlarms(List<UUID> listDivisionUUID, String token) {

        return new ArrayList<>();
    }

    public List<DivisionDto> activeAlarms(List<UUID> listDivisionUUID, String token) {
        return null;
    }

    private Set<Division> getDivisionsOfUser(List<UUID> listDivisionUUID, User u){
        return null;
    }

    public RegisterPageable getLogs(Integer pageNumber, Integer pageSize, String order, Date date, String field) {
        return null;
    }

    public void leave(String token) {

    }

    public void goTo(String token, Optional<UUID> uuids) {

    }

    public List<DivisionDto> activeOrDeactive(List<UUID> uuids, String token) {
        return null;
    }
}
