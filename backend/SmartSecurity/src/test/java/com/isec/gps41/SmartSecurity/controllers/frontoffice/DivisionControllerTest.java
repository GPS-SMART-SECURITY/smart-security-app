package com.isec.gps41.SmartSecurity.controllers.frontoffice;

import com.isec.gps41.SmartSecurity.model.Division;
import com.isec.gps41.SmartSecurity.payload.ListUUID;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.*;

public class DivisionControllerTest extends AuthenticationUserTest{

    @Test
    public void goToAndLeaveTest() throws Exception {

        String uri = "/api/fo/division/";
        ListUUID listUUID = new ListUUID();
        listUUID.setUuids(u.getDivisions().stream().map(Division::getUuid).toList());

        String input = super.mapToJson(listUUID);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri+"goto")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", token).content(input)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(status, 200);

        mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri+"leave")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", token)).andReturn();

        status = mvcResult.getResponse().getStatus();
        assertEquals(status, 200);
    }
}