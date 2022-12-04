package com.isec.gps41.SmartSecurity.service;

import com.isec.gps41.SmartSecurity.AbstractTest;
import com.isec.gps41.SmartSecurity.constants.ROLES;
import com.isec.gps41.SmartSecurity.exception.ResourcesInvalid;
import com.isec.gps41.SmartSecurity.model.Division;
import com.isec.gps41.SmartSecurity.model.TypeDivision;
import com.isec.gps41.SmartSecurity.model.User;
import com.isec.gps41.SmartSecurity.payload.BuildingDetailsRequest;
import com.isec.gps41.SmartSecurity.payload.UserDto;
import com.isec.gps41.SmartSecurity.payload.users.UserNewRequest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

import java.util.*;

public class BuildingServiceTest extends AbstractTest {

    UserNewRequest user;
    User admin;
    UserDto userDto, createdUser, updatedUser;
    @Autowired
    public BuildingService buildingService;


    @Before
    public void setUp() {
        super.setUp();

        userDto = new UserDto();
        userDto.setName("Hipólito");
        userDto.setBirthDate(new Date(1990+1900, Calendar.FEBRUARY,28));
        userDto.setEmail("hipo@gmail.com");

        String password = "!ASDasd123";

        List<UUID> divisionsUUIDList = divisionService.getAll().stream()
                .filter(division -> division.getType() == TypeDivision.COMMON_AREA)
                .map(division -> division.getUuid())
                .toList();

        user = new UserNewRequest(userDto, password, divisionsUUIDList);
        admin = userService.findByEmail("daniel@gmail.com", true);

    }

    /**
     * Testar se o método newUser cria o user coerente
     * com o user inserido
     */
    @Test
    public void newUser() {
        String to_ken = authService.generateToken(admin.getEmail(),admin.getId());

        createdUser = buildingService.newUser(user, to_ken);

        assertEquals(userDto.getName(), createdUser.getName());
        assertEquals(userDto.getEmail(), createdUser.getEmail());
        assertEquals(userDto.getBirthDate(), createdUser.getBirthDate());
        assertEquals(ROLES.USER, createdUser.getRole());
        assertTrue(createdUser.isActive());

    }

    /**
     * Testar se o método updateUser throws ResourcesInvalid
     */
    @Test (expected = ResourcesInvalid.class)
    public void updateUserThrowsResourcesInvalid() {
        User user = userService.findByEmail("henrique@gmail.com",true);
        UserDto userToUpdate = UserDto.maptoDto2(user);
        userToUpdate.setEmail("hugo@gmail.com");
        List<UUID> divisionsUUIDList = user.getDivisions().stream().map(division -> division.getUuid()).toList();
        UserNewRequest userNewRequest = new UserNewRequest(userToUpdate, user.getPassword(),divisionsUUIDList);

        updatedUser = buildingService.updateUser(userNewRequest, user.getUuid());

    }

    /**
     * Testar se o método updateUser atualiza o utilizador
     * conforme os dados fornecidos
     */
    @Test
    public void updateUser() {
        User user = userService.findByEmail("henrique@gmail.com",true);
        UserDto userToUpdate = UserDto.maptoDto2(user);
        userToUpdate.setEmail("joao@gmail.com");
        userToUpdate.setName("Joao");
        userToUpdate.setBirthDate(new Date(1998+1900, Calendar.OCTOBER,17));

        List<UUID> divisionsUUIDList = user.getDivisions().stream().map(division -> division.getUuid()).toList();

        UserNewRequest userNewRequest = new UserNewRequest(userToUpdate, "!Asd123", divisionsUUIDList);

        updatedUser = buildingService.updateUser(userNewRequest, user.getUuid());

        assertEquals(userToUpdate.getEmail(), updatedUser.getEmail());
        assertEquals(userToUpdate.getName(), updatedUser.getName());
        assertEquals(userToUpdate.getBirthDate(), updatedUser.getBirthDate());

    }

    /**
     * Testar se o método getBuildingDetails devolve o mesmo número de
     * divisões que as divisões existentes
     */
    @Test
    public void getBuildingDetails() {
        List<Division> divisions = divisionService.getAll().stream().toList();
        BuildingDetailsRequest request = buildingService.getBuildingDetails();

        int floorDtoDivisionsCount = request.getFloorDtos().stream().mapToInt(floorDto -> floorDto.getDivisions().size()).sum();

        assertEquals(divisions.size(), floorDtoDivisionsCount);
    }

    /**
     * Testar se o método inativeUser muda o estado do user
     * isActive para false
     */
    @Test
    public void inativeUser() {
        User user = userService.findByEmail("hugo@gmail.com", true);

        buildingService.inativeUser(user.getUuid());

        assertFalse(userService.findUserByUUID(user.getUuid()).isActive());
    }

}