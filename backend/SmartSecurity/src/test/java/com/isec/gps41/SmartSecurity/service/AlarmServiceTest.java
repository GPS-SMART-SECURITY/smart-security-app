package com.isec.gps41.SmartSecurity.service;

import com.isec.gps41.SmartSecurity.AbstractTest;
import com.isec.gps41.SmartSecurity.exception.ResourcesInvalid;
import com.isec.gps41.SmartSecurity.model.Alarm;
import com.isec.gps41.SmartSecurity.model.Division;
import com.isec.gps41.SmartSecurity.model.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class AlarmServiceTest extends AbstractTest {

    @Autowired
    AlarmService alarmService;

    @Before
    public void setUp() {
        super.setUp();
    }


    /**
     * Testar se consegue desativar um alarme usando o path
     */
    @Test
    public void desativateOnlyOneAlarmIfIsAtivate() {
        User user = userService.findByEmail("hugo@gmail.com", true);
        Set<Division> divisions = new HashSet<>();
        ((Division) user.getDivisions().toArray()[0]).getDivisionsDependsOf().forEach(division -> divisions.add(division));

        // Desativar apenas um alarme
        alarmService.desativateAlarmeIfIsNotAtivate(divisions, user);

        divisions.forEach(division -> assertFalse(division.getAlarm().isOn()));
    }


    /**
     * Testar se desativateAlarmeIfIsNotAtivate throws ResourcesInvalid
     */
    @Test(expected = ResourcesInvalid.class)
    public void desativateAlarmeIfIsNotAtivateThrowsResourcesInvalid() {
        User user = userService.findByEmail("hugo@gmail.com", true);
        Set<Division> divisions = new HashSet<>();
        divisions.add((Division) user.getDivisions().toArray()[0]);

        // Desativar apenas um alarme
        alarmService.desativateAlarmeIfIsNotAtivate(divisions, user);
        Alarm alarm = ((Division) user.getDivisions().toArray()[0]).getAlarm();

        // Voltar a tentar desativar o mesmo alarme
        alarmService.desativateAlarmeIfIsNotAtivate(divisions, user);
    }

    /**
     * Testar se consegue ligar todos os alarmes
     */
    @Test
    public void activeAlarms() {
        User user = userService.findByEmail("hugo@gmail.com", true);

        // Desativar todos os alarmes
        alarmService.desativateAlarmeIfIsNotAtivate(user.getDivisions(), user);

        // Tentar ativar todos os alarmes
        alarmService.activeAlarms(user.getDivisions(), user);

        // Testar se todos estão ativados
        user.getDivisions().forEach(division -> assertTrue(division.getAlarm().isOn()));
    }

    /**
     * testar se ativa apenas um alarme
     */
    @Test
    public void ActivateAlarms() {
        User user = userService.findByEmail("hugo@gmail.com", true);

        // Desativar apenas um alarme
        alarmService.desativateAlarmeIfIsNotAtivate(user.getDivisions(), user);

        Set<Division> divisions = new HashSet<>();
        divisions.add((Division) user.getDivisions().toArray()[0]);
        Alarm alarm = ((Division) user.getDivisions().toArray()[0]).getAlarm();

        alarmService.activeAlarms(divisions, user);
        assertTrue(alarm.isOn());
    }

    /**
     * Testar se o método ActivateAlarms throws ResourceInvalid
     */
    @Test (expected = ResourcesInvalid.class)
    public void activateAlarmsThrowsResourceInvalid() {
        User user = userService.findByEmail("hugo@gmail.com", true);

        // Tentar ativar todos os alarmes
        alarmService.activeAlarms(user.getDivisions(), user);
    }


    /**
     * Testar se quando sai, todos os alarmes estão desativados
     */
    @Test
    public void leave() {
        User user = userService.findByEmail("hugo@gmail.com", true);

        alarmService.leave(user);

        user.getDivisions().forEach(division -> assertTrue(division.getAlarm().isOn()));
    }

    @Test
    public void goTo() {
        User user = userService.findByEmail("hugo@gmail.com", true);

        Set<Division> divisionToSend = new HashSet<>();
        ((Division) user.getDivisions().toArray()[0]).getDivisionsDependsOf().forEach(division -> divisionToSend.add(division));

        alarmService.goTo(user, divisionToSend);

        divisionToSend.forEach(division -> assertFalse(division.getAlarm().isOn()));
    }

}