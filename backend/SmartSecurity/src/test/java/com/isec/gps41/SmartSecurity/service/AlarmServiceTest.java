package com.isec.gps41.SmartSecurity.service;

import com.isec.gps41.SmartSecurity.AbstractTest;
import com.isec.gps41.SmartSecurity.exception.ResourcesInvalid;
import com.isec.gps41.SmartSecurity.model.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class AlarmServiceTest extends AbstractTest {

    @Autowired
    AlarmService alarmService;

    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void desativateAlarmeIfIsNotAtivate() {
        User user = userService.findByEmail("hugo@gmail.com", true);

        // Desativar todos os alarmes
        alarmService.desativateAlarmeIfIsNotAtivate(user.getDivisions(), user);

        user.getDivisions().forEach(division -> assertFalse(division.getAlarm().isOn()));

        // Testar ver se todos os alarmes estão desativos
        alarmService.desativateAlarmeIfIsNotAtivate(user.getDivisions(), user);
        user.getDivisions().forEach(division -> assertThrows(ResourcesInvalid, );


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

    @Test
    public void activeAlarmsGuard() {
    }

    @Test
    public void desativateAlarmsSecurityGuard() {
    }
}