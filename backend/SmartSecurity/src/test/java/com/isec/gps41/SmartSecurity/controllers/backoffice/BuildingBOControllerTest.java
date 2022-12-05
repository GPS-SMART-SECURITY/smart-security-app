package com.isec.gps41.SmartSecurity.controllers.backoffice;


import com.isec.gps41.SmartSecurity.model.Division;
import com.isec.gps41.SmartSecurity.model.TypeDivision;
import com.isec.gps41.SmartSecurity.payload.ListUUID;
import com.isec.gps41.SmartSecurity.payload.ResultOfDesativeAtiveAlarms;
import com.isec.gps41.SmartSecurity.payload.division.DivisionDto;
import com.isec.gps41.SmartSecurity.repository.AlarmRepository;
import com.isec.gps41.SmartSecurity.repository.DivisionRepository;
import com.isec.gps41.SmartSecurity.repository.RegisterRepository;
import com.isec.gps41.SmartSecurity.repository.UserRepository;
import com.isec.gps41.SmartSecurity.security.JwtTokenProvider;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class BuildingBOControllerTest extends AuthenticationSecurityGuardTest {


    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    UserRepository userRepository;

    @Autowired
    DivisionRepository divisionRepository;

    @Autowired
    RegisterRepository registerRepository;

    @Autowired
    AlarmRepository alarmRepository;


    @Test
    public void getUsers() throws Exception {
        String uri = "/api/bo/building";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", token)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void desactiveAlarmSecurity() throws Exception {
        String uri = "/api/bo/building/desactiveAlarms";
        List<Division> d = divisionRepository.findAll().stream()
                .filter(division -> division.getType() == TypeDivision.OFFICE).toList();
        d = List.of(d.get(0), d.get(1));
        ListUUID listUUID = new ListUUID();
        listUUID.setUuids(d.stream().map(Division::getUuid).toList());
        String input = super.mapToJson(listUUID);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", token)
                .content(input)).andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
        ResultOfDesativeAtiveAlarms response = mapFromJson(mvcResult.getResponse().getContentAsString(),
                ResultOfDesativeAtiveAlarms.class);
        response.getDivisions().forEach(div -> assertFalse(div.isOn()));

    }

    @Test
    public void activeAlarms() throws Exception {
        String uri = "/api/bo/building/activeAlarms";

        List<Division> d = divisionRepository.findAll().stream()
                .filter(division -> division.getType() == TypeDivision.OFFICE).toList();
        d = List.of(d.get(0), d.get(1));
        d.forEach(division -> {
            division.getAlarm().setOn(false);
            divisionRepository.save(division);
        });
        ListUUID listUUID = new ListUUID();
        listUUID.setUuids(d.stream().map(Division::getUuid).toList());
        String input = super.mapToJson(listUUID);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", token)
                .content(input)).andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
        ResultOfDesativeAtiveAlarms response = mapFromJson(mvcResult.getResponse().getContentAsString(),
                ResultOfDesativeAtiveAlarms.class);
        response.getDivisions().forEach(div -> assertTrue(div.isOn()));

    }

}
