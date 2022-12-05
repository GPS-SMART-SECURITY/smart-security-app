package com.isec.gps41.SmartSecurity.controllers.backoffice;

import com.isec.gps41.SmartSecurity.constants.ROLES;
import com.isec.gps41.SmartSecurity.exception.GlobalExceptionHandler;
import com.isec.gps41.SmartSecurity.exception.ResourcesInvalid;
import com.isec.gps41.SmartSecurity.model.Division;
import com.isec.gps41.SmartSecurity.model.TypeDivision;
import com.isec.gps41.SmartSecurity.payload.UserDto;
import com.isec.gps41.SmartSecurity.payload.users.UserNewRequest;
import com.isec.gps41.SmartSecurity.repository.AlarmRepository;
import com.isec.gps41.SmartSecurity.repository.DivisionRepository;
import com.isec.gps41.SmartSecurity.repository.RegisterRepository;
import com.isec.gps41.SmartSecurity.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;

import static org.junit.Assert.*;

public class UsersBOControllerTest extends AuthenticationSecurityGuardTest {


    @Autowired
    UserRepository userRepository;

    @Autowired
    DivisionRepository divisionRepository;

    @Autowired
    RegisterRepository registerRepository;

    @Autowired
    AlarmRepository alarmRepository;

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void createUser() throws Exception {
        String uri = "/api/bo/users/new";

        UserNewRequest userNewRequest = new UserNewRequest();
        UserDto userDto = new UserDto();
        userDto.setEmail("teste@gmail.com");
        userDto.setName("Teste");
        userDto.setBirthDate(new Date(1990, 01, 01));
        userNewRequest.setPassword("Asd123!");
        userNewRequest.setUser(userDto);
        userNewRequest.setDivisions(divisionRepository.findAll().stream().filter(d -> d.getType() == TypeDivision.OFFICE).map(Division::getUuid).toList());
        String inputJson = super.mapToJson(userNewRequest);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", token).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(status, 201);
        UserDto result = super.mapFromJson(mvcResult.getResponse().getContentAsString(), UserDto.class);
        assertNotNull(result);
        assertTrue(result.getUuid().toString().length() > 1);
        assertEquals(ROLES.USER, result.getRole().toString());
    }

    @Test()
    public void updateUserCorrect() throws Exception {
        UserNewRequest userNewRequest = new UserNewRequest();
        UserDto userDto = UserDto.maptoDto(userService.findByEmail("hugo@gmail.com", true));
        String uri = "/api/bo/users/" + userDto.getUuid();
        userDto.setEmail("hugoteste@gmail.com");
        userDto.setName("Hugo Teste");
        userDto.setBirthDate(new Date(98, 01, 01));
        userNewRequest.setPassword("Teste123!");
        userNewRequest.setUser(userDto);
        userNewRequest.setDivisions(divisionRepository.findAll().stream().filter(d -> d.getType() == TypeDivision.OFFICE).map(Division::getUuid).toList());
        String inputJson = super.mapToJson(userNewRequest);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", token).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(status, 200);
        UserDto result = super.mapFromJson(mvcResult.getResponse().getContentAsString(), UserDto.class);
        assertEquals("hugoteste@gmail.com", result.getEmail());
        assertEquals("Hugo Teste", result.getName());

    }

    @Test(expected = ResourcesInvalid.class)
    public void updateUserWithInvalidPassword() throws Throwable {
        UserNewRequest userNewRequest = new UserNewRequest();
        UserDto userDto = UserDto.maptoDto(userService.findByEmail("hugo@gmail.com", true));
        String uri = "/api/bo/users/" + userDto.getUuid();
        userDto.setEmail("hugoteste@gmail.com");
        userDto.setName("Hugo Teste");
        userDto.setBirthDate(new Date(98, 01, 01));
        userNewRequest.setPassword("Tes");
        userNewRequest.setUser(userDto);
        userNewRequest.setDivisions(divisionRepository.findAll().stream().filter(d -> d.getType() == TypeDivision.OFFICE).map(Division::getUuid).toList());
        String inputJson = super.mapToJson(userNewRequest);

        try {
            MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .header("Authorization", token).content(inputJson)).andReturn();
        }catch (Exception e){
            throw e.getCause();
        }

    }


}
