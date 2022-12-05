package com.isec.gps41.SmartSecurity.controllers.frontoffice;

import com.isec.gps41.SmartSecurity.model.Division;
import com.isec.gps41.SmartSecurity.payload.ListUUID;
import com.isec.gps41.SmartSecurity.payload.UserDto;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;

public class UserControllerTest extends AuthenticationUserTest {


    @Test
    public void getDetailsStatusTest() throws Exception {
        String uri = "/api/fo/users";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", token)).andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());

    }
    @Test
    public void getDetailsResponseTest() throws Exception {
        String uri = "/api/fo/users";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", token)).andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
        UserDto userDto = mapFromJson(mvcResult.getResponse().getContentAsString(),UserDto.class);
        assertEquals(u.getEmail(), userDto.getEmail());

    }

}
