package com.isec.gps41.SmartSecurity.service;

import com.isec.gps41.SmartSecurity.AbstractTest;
import com.isec.gps41.SmartSecurity.constants.ROLES;
import com.isec.gps41.SmartSecurity.exception.ResourcesInvalid;
import com.isec.gps41.SmartSecurity.model.TypeDivision;
import com.isec.gps41.SmartSecurity.model.User;
import com.isec.gps41.SmartSecurity.payload.LoginRequest;
import com.isec.gps41.SmartSecurity.payload.UserDto;
import com.isec.gps41.SmartSecurity.payload.users.UserNewRequest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

public class AuthServiceTest extends AbstractTest {

    LoginRequest loginRequest;

    @Before
    public void setUp() {
        super.setUp();

        loginRequest = new LoginRequest("anibal@gmail.com", "!Asd123");

    }
    @Test
    public void registerUser() {
        User user =  authService.registerUser(loginRequest);

        assertEquals(loginRequest.getEmail(), user.getEmail());
        assertEquals(ROLES.USER_ROLE, user.getRole());
        assertTrue(user.isActive());
    }
    @Test(expected = ResourcesInvalid.class)
    public void registerUserThrowResourcesInvalid() {
        loginRequest.setEmail("hugo@gmail.com");
        authService.registerUser(loginRequest);
    }

    @Test
    public void login() {
    }
}