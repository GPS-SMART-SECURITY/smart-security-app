package com.isec.gps41.SmartSecurity.controllers;

import com.isec.gps41.SmartSecurity.AbstractTest;
import org.junit.Before;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;

public class ControllerTest extends AbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken("test", null, new ArrayList<>()));
    }

//    @Test
//    public void buildDb() throws Exception {
//        String uri = "/test/buildDB";
//
//        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                ).andReturn();
//
//        int status = mvcResult.getResponse().getStatus();
//        assertEquals(200, status);
//    }

}
