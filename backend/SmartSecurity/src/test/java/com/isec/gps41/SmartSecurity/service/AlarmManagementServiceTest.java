package com.isec.gps41.SmartSecurity.service;

import com.isec.gps41.SmartSecurity.AbstractTest;
import com.isec.gps41.SmartSecurity.model.TypeDivision;
import com.isec.gps41.SmartSecurity.model.User;
import com.isec.gps41.SmartSecurity.payload.division.DivisionDto;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.springframework.security.config.http.MatcherType.mvc;

public class AlarmManagementServiceTest extends AbstractTest {

    @Autowired
    AlarmManagementService alarmManagementService;

    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void desativateAlarms() {
        User user = userService.findByEmail("daniel@gmail.com", true);
        String token = authService.generateToken(user.getEmail(), user.getId());

        List<UUID> divisionsUser = user.getDivisions().stream().filter(division -> division.getType() == TypeDivision.OFFICE)
                                                                .map(division -> division.getUuid()).toList();

        List<DivisionDto> result = alarmManagementService.desativateAlarms(divisionsUser, token);


    }

    @Test
    public void activeAlarms() {

    }

    @Test
    public void leave() {

    }

    @Test
    public void goTo() {

    }
}