package com.isec.gps41.SmartSecurity.service;

import com.isec.gps41.SmartSecurity.AbstractTest;
import com.isec.gps41.SmartSecurity.model.Division;
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
import java.util.Optional;
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

        List<UUID> divisionsUser = divisionService.getAll().stream().filter(division -> division.getType() == TypeDivision.OFFICE)
                                                                .map(Division::getUuid).toList();

        List<DivisionDto> result = alarmManagementService.desativateAlarms(divisionsUser, token);
        result.forEach(divisionDto -> assertFalse(divisionDto.isOn()));

    }

    @Test
    public void desativateAlarmsWithPersonInRoom() {
        User user = userService.findByEmail("daniel@gmail.com", true);
        String token = authService.generateToken(user.getEmail(), user.getId());

        User user1 = userService.findByEmail("hugo@gmail.com", true);
        Division div = user1.getDivisions().stream().filter(division1 -> division1.getType() == TypeDivision.OFFICE).findFirst().get();
        String tokenUser = authService.generateToken(user1.getEmail(), user1.getId());

        alarmManagementService.goTo(tokenUser, Optional.ofNullable(div.getUuid()));
        List<UUID> divisionsUser = divisionService.getAll().stream().filter(division -> division.getType() == TypeDivision.OFFICE)
                .map(Division::getUuid).toList();

        List<DivisionDto> result = alarmManagementService.desativateAlarms(divisionsUser, token);
        result.forEach(divisionDto -> assertFalse(divisionDto.isOn()));
    }

    @Test
    public void activateAlarmsWithPersonInRoom() {
        User user = userService.findByEmail("daniel@gmail.com", true);
        String token = authService.generateToken(user.getEmail(), user.getId());

        User user1 = userService.findByEmail("hugo@gmail.com", true);
        Division div = user1.getDivisions().stream().filter(division1 -> division1.getType() == TypeDivision.OFFICE).findFirst().get();
        String tokenUser = authService.generateToken(user1.getEmail(), user1.getId());

        alarmManagementService.goTo(tokenUser, Optional.ofNullable(div.getUuid()));
        List<UUID> divisionsUser = divisionService.getAll().stream().filter(division -> division.getType() == TypeDivision.OFFICE)
                .map(Division::getUuid).toList();

        List<DivisionDto> result = alarmManagementService.activeAlarms(divisionsUser, token);
        DivisionDto divisiontToTest = result.stream().filter(divisionDto -> divisionDto.getUuid() == div.getUuid()).findFirst().get();
        assertTrue(divisiontToTest.isOn());
        //List<DivisionDto> divisionsToTest = result.stream().filter(divisionDto -> div.getDivisionsDependsOf().e);
    }



    @Test
    public void activeAlarms() {

    }

}