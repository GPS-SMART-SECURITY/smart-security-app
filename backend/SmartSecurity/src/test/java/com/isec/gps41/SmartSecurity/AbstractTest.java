package com.isec.gps41.SmartSecurity;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.isec.gps41.SmartSecurity.controllers.backoffice.UsersBOControllerTest;
import com.isec.gps41.SmartSecurity.exception.GlobalExceptionHandler;
import com.isec.gps41.SmartSecurity.repository.DivisionRepository;
import com.isec.gps41.SmartSecurity.repository.UserRepository;
import com.isec.gps41.SmartSecurity.security.JwtTokenProvider;
import com.isec.gps41.SmartSecurity.service.AlarmService;
import com.isec.gps41.SmartSecurity.service.AuthService;
import com.isec.gps41.SmartSecurity.service.DivisionService;
import com.isec.gps41.SmartSecurity.service.UserService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SmartSecurityApplication.class)
@WebAppConfiguration
@Transactional
public class AbstractTest {


    protected MockMvc mvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    public UserService userService;

    @Autowired
    public DivisionService divisionService;

    @Autowired
    public AlarmService alarmService;

    @Autowired
    public AuthService authService;

    @Autowired
    public JwtTokenProvider tokenProvider;

    protected void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        //mvc = MockMvcBuilders.standaloneSetup(new UsersBOControllerTest()).setControllerAdvice(new GlobalExceptionHandler()).build();
    }

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }
}
