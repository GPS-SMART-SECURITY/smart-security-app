package com.isec.gps41.SmartSecurity.controllers.backoffice;

import com.isec.gps41.SmartSecurity.model.Register;
import com.isec.gps41.SmartSecurity.payload.UserDto;
import com.isec.gps41.SmartSecurity.payload.log.RegisterPageable;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.Assert.*;

public class LogControllerTest extends AuthenticationSecurityGuardTest{

    @Test
    public void index() throws Exception {
        String uri = "/api/bo/log?pageNo=0";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", token)).andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());

        RegisterPageable response = mapFromJson(mvcResult.getResponse().getContentAsString(),RegisterPageable.class);
        assertNotNull(response.getRegisters());

    }
}