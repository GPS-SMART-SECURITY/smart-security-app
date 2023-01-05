package com.isec.gps41.SmartSecurity.controllers;

import com.isec.gps41.SmartSecurity.constants.ROLES;
import com.isec.gps41.SmartSecurity.model.*;
import com.isec.gps41.SmartSecurity.payload.UserDto;
import com.isec.gps41.SmartSecurity.payload.division.DivisionDto;
import com.isec.gps41.SmartSecurity.payload.users.UserNewRequest;
import com.isec.gps41.SmartSecurity.repository.AlarmRepository;
import com.isec.gps41.SmartSecurity.repository.DivisionRepository;
import com.isec.gps41.SmartSecurity.repository.FloorRepository;
import com.isec.gps41.SmartSecurity.repository.UserRepository;
import com.isec.gps41.SmartSecurity.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    UserRepository userRepository;

    @Autowired
    FloorRepository floorRepository;

    @Autowired
    DivisionRepository divisionRepository;

    @Autowired
    AlarmRepository alarmRepository;

    @GetMapping()
    public ResponseEntity<String> testeAuth(@RequestHeader("Authorization") String token){

        token = token.substring(7);
        String email = tokenProvider.getEmailByToken(token);
        //User user = userRepository.findByEmail(email);

        User user  = new User();
        user.setName("Daniel Fernandes");
        user.setEmail("daniel@gmail.com");
        user.setBirthDate(new Date(1996, 03, 18));
        user.setRole(ROLES.SECURITY_GUARD_ROLE);
        user.setPassword(new BCryptPasswordEncoder().encode("asd123"));


        userRepository.save(user);


        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }


    @GetMapping("/buildDB")
    public ResponseEntity<String> buildDB(){

        Floor groundFloor = new Floor();
        groundFloor.setNumber(0);

        Floor firstFloor = new Floor();
        firstFloor.setNumber(1);

        //
        // Divisions
        //

        //
        // Ground Floor
        //

        Division entrance = new Division();
        entrance.setFloor(groundFloor);
        entrance.setType(TypeDivision.COMMON_AREA);
        entrance.setName("Entrance");

        Division room01 = new Division();
        room01.setFloor(groundFloor);
        room01.setType(TypeDivision.OFFICE);
        room01.setName("Room 01");

        Division room02 = new Division();
        room02.setFloor(groundFloor);
        room02.setType(TypeDivision.OFFICE);
        room02.setName("Room 02");

        Division room03 = new Division();
        room03.setFloor(groundFloor);
        room03.setType(TypeDivision.OFFICE);
        room03.setName("Room 03");

        Division room04 = new Division();
        room04.setFloor(groundFloor);
        room04.setType(TypeDivision.OFFICE);
        room04.setName("Room 04");

        Division bathroom01  = new Division();
        bathroom01.setFloor(groundFloor);
        bathroom01.setType(TypeDivision.COMMON_AREA);
        bathroom01.setName("Bathroom 01");

        Division hallway01  = new Division();
        hallway01.setFloor(groundFloor);
        hallway01.setType(TypeDivision.COMMON_AREA);
        hallway01.setName("Hallway 01");

        //
        // First Floor
        //

        Division room11 = new Division();
        room11.setFloor(firstFloor);
        room11.setType(TypeDivision.OFFICE);
        room11.setName("Room 11");

        Division room12 = new Division();
        room12.setFloor(firstFloor);
        room12.setType(TypeDivision.OFFICE);
        room12.setName("Room 12");

        Division room13 = new Division();
        room13.setFloor(firstFloor);
        room13.setType(TypeDivision.OFFICE);
        room13.setName("Room 13");

        Division room14 = new Division();
        room14.setFloor(firstFloor);
        room14.setType(TypeDivision.OFFICE);
        room14.setName("Room 14");

        Division room15 = new Division();
        room15.setFloor(firstFloor);
        room15.setType(TypeDivision.OFFICE);
        room15.setName("Room 15");

        Division bathroom11  = new Division();
        bathroom11.setFloor(firstFloor);
        bathroom11.setType(TypeDivision.COMMON_AREA);
        bathroom11.setName("Bathroom 11");

        Division hallway11  = new Division();
        hallway11.setFloor(firstFloor);
        hallway11.setType(TypeDivision.COMMON_AREA);
        hallway11.setName("Hallway 11");

        entrance = divisionRepository.save(entrance);
        hallway11 = divisionRepository.save(hallway11);
        bathroom01 = divisionRepository.save(bathroom01);
        bathroom11 = divisionRepository.save(bathroom11);
        hallway01 = divisionRepository.save(hallway01);
        room01 = divisionRepository.save(room01);
        room02 = divisionRepository.save(room02);
        room03 = divisionRepository.save(room03);
        room04 = divisionRepository.save(room04);
        room11 = divisionRepository.save(room11);
        room12 = divisionRepository.save(room12);
        room13 = divisionRepository.save(room13);
        room14 = divisionRepository.save(room14);

        //
        // Set room dependecies
        //

        hallway01.getDivisionsDependsOf().addAll(List.of(entrance, bathroom01));
        room01.getDivisionsDependsOf().addAll(List.of(hallway01, entrance, bathroom01));
        room02.getDivisionsDependsOf().addAll(List.of(hallway01, entrance, bathroom01));
        room03.getDivisionsDependsOf().addAll(List.of(hallway01, entrance, bathroom01));
        room04.getDivisionsDependsOf().addAll(List.of(hallway01, entrance, bathroom01));
        bathroom01.getDivisionsDependsOf().addAll(List.of(hallway01, entrance));
        hallway11.getDivisionsDependsOf().addAll(List.of(entrance, hallway01, bathroom01, bathroom11));
        room11.getDivisionsDependsOf().addAll(List.of(hallway01,hallway11, entrance, bathroom01, bathroom11));
        room12.getDivisionsDependsOf().addAll(List.of(hallway01,hallway11, entrance, bathroom01, bathroom11));
        room13.getDivisionsDependsOf().addAll(List.of(hallway01,hallway11, entrance, bathroom01, bathroom11));
        room14.getDivisionsDependsOf().addAll(List.of(hallway01,hallway11, entrance, bathroom01, bathroom11));
        bathroom11.getDivisionsDependsOf().addAll(List.of( hallway01, hallway11, bathroom01, entrance));

        Alarm alarmEntrance  = new Alarm();
        alarmEntrance.setOn(true);
        Alarm alarmHallway01 = new Alarm();
        alarmHallway01.setOn(true);
        Alarm alarmRoom01 = new Alarm();
        alarmRoom01.setOn(true);
        Alarm alarmRoom02 = new Alarm();
        alarmRoom02.setOn(true);
        Alarm alarmRoom03 = new Alarm();
        alarmRoom03.setOn(true);
        Alarm alarmRoom04 = new Alarm();
        alarmRoom04.setOn(true);
        Alarm alarmBathroom01 = new Alarm();
        alarmBathroom01.setOn(true);

        Alarm alarmHallway11 = new Alarm();
        alarmHallway11.setOn(true);
        Alarm alarmRoom11 = new Alarm();
        alarmRoom11.setOn(true);
        Alarm alarmRoom12 = new Alarm();
        alarmRoom12.setOn(true);
        Alarm alarmRoom13 = new Alarm();
        alarmRoom13.setOn(true);
        Alarm alarmRoom14 = new Alarm();
        alarmRoom14.setOn(true);
        Alarm alarmBathroom11 = new Alarm();
        alarmBathroom11.setOn(true);

        alarmEntrance.setName(entrance.getName() + " Alarm");
        alarmHallway01.setName(hallway01.getName() + " Alarm");
        alarmRoom01.setName(room01.getName() + " Alarm");
        alarmRoom02.setName(room02.getName() + " Alarm");
        alarmRoom03.setName(room03.getName() + " Alarm");
        alarmRoom04.setName(room04.getName() + " Alarm");
        alarmBathroom01.setName(bathroom01.getName() + " Alarm");
        alarmHallway11.setName(hallway11.getName() + " Alarm");
        alarmRoom11.setName(room11.getName() + " Alarm");
        alarmRoom12.setName(room12.getName() + " Alarm");
        alarmRoom13.setName(room13.getName() + " Alarm");
        alarmRoom14.setName(room14.getName() + " Alarm");
        alarmBathroom11.setName(bathroom11.getName() + " Alarm");

        alarmRepository.save(alarmEntrance);
        alarmRepository.save(alarmHallway01);
        alarmRepository.save(alarmRoom01);
        alarmRepository.save(alarmRoom02);
        alarmRepository.save(alarmRoom03);
        alarmRepository.save(alarmRoom04);
        alarmRepository.save(alarmBathroom01);
        alarmRepository.save(alarmHallway11);
        alarmRepository.save(alarmRoom11);
        alarmRepository.save(alarmRoom12);
        alarmRepository.save(alarmRoom13);
        alarmRepository.save(alarmRoom14);
        alarmRepository.save(alarmBathroom11);

        floorRepository.save(groundFloor);
        floorRepository.save(firstFloor);

        entrance = divisionRepository.save(entrance);
        hallway11 = divisionRepository.save(hallway11);
        bathroom01 = divisionRepository.save(bathroom01);
        bathroom11 = divisionRepository.save(bathroom11);
        hallway01 = divisionRepository.save(hallway01);
        room01 = divisionRepository.save(room01);
        room02 = divisionRepository.save(room02);
        room03 = divisionRepository.save(room03);
        room04 = divisionRepository.save(room04);
        room11 = divisionRepository.save(room11);
        room12 = divisionRepository.save(room12);
        room13 = divisionRepository.save(room13);
        room14 = divisionRepository.save(room14);


        entrance.setAlarm(alarmEntrance);
        hallway01.setAlarm(alarmHallway01);
        room01.setAlarm(alarmRoom01);
        room02.setAlarm(alarmRoom02);
        room03.setAlarm(alarmRoom03);
        room04.setAlarm(alarmRoom04);
        bathroom01.setAlarm(alarmBathroom01);
        hallway11.setAlarm(alarmHallway11);
        room11.setAlarm(alarmRoom11);
        room12.setAlarm(alarmRoom12);
        room13.setAlarm(alarmRoom13);
        room14.setAlarm(alarmRoom14);
        bathroom11.setAlarm(alarmBathroom11);





        User user;
        user = createUser("Daniel", "daniel@gmail.com", new Date(96, 03, 18),
                ROLES.SECURITY_GUARD_ROLE, "asd123");
        userRepository.save(user);

        user = createUser("Henrique", "henrique@gmail.com", new Date(98, 11, 07),
                ROLES.USER_ROLE, "asd123");
        user.setDivisions(Set.of(entrance, room01, room02, room11, hallway01, hallway11, bathroom01, bathroom11));
        userRepository.save(user);

        user = createUser("Hugo Jorge", "hugo@gmail.com", new Date(85, 03, 20),
                ROLES.USER_ROLE, "asd123");
        user.setDivisions(Set.of(entrance, room11, room04, room12, room13, hallway01, hallway11, bathroom01, bathroom11));
        userRepository.save(user);

        user = createUser("João Costa", "joao@gmail.com", new Date(96, 10, 13),
                ROLES.USER_ROLE, "asd123");
        user.setDivisions(Set.of(entrance, room11, room04, room02, room15, room14, room13, hallway01, hallway11, bathroom01, bathroom11));
        userRepository.save(user);

        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }


    private User createUser(String name, String email, Date birthDate, GrantedAuthority role, String password){
        User user  = new User();
        user.setName(name);
        user.setEmail(email);
        user.setBirthDate(birthDate);
        user.setRole(role);
        user.setPassword(new BCryptPasswordEncoder().encode(password));

        return user;
    }
    //only for test
    @GetMapping("/division")
    public ResponseEntity<String> addDivision(@RequestHeader("Authorization") String token){

        Floor floor = new Floor();
        floor.setNumber(0);

        Floor floor1 = new Floor();
        floor.setNumber(1);

        Division division = new Division();
        division.setFloor(floor);
        division.setType(TypeDivision.COMMON_AREA);
        division.setName("Entrada");

        Division division6 = new Division();
        division6.setFloor(floor1);
        division6.setType(TypeDivision.COMMON_AREA);
        division6.setName("Corredor");
        division6.getDivisionsDependsOf().add(division);

        Division division1 = new Division();
        division1.setFloor(floor1);
        division1.setType(TypeDivision.COMMON_AREA);
        division1.setName("LAS");
        division1.getDivisionsDependsOf().add(division);
        division1.getDivisionsDependsOf().add(division6);

        Division division2 = new Division();
        division2.setFloor(floor1);
        division2.setType(TypeDivision.OFFICE);
        division2.setName("LIS");
        division2.getDivisionsDependsOf().add(division);
        division2.getDivisionsDependsOf().add(division6);

        Division division3 = new Division();
        division3.setFloor(floor1);
        division3.setType(TypeDivision.COMMON_AREA);
        division3.setName("Casa de banho");
        division3.getDivisionsDependsOf().add(division);
        division3.getDivisionsDependsOf().add(division6);

        Division division4 = new Division();
        division4.setFloor(floor);
        division4.setType(TypeDivision.OFFICE);
        division4.setName("Anfiteatro");
        division4.getDivisionsDependsOf().add(division);


        Division division5 = new Division();
        division5.setFloor(floor);
        division5.setType(TypeDivision.OFFICE);
        division5.setName("Secretaria");
        division5.getDivisionsDependsOf().add(division);


        Alarm a = new Alarm();
        a.setOn(true);
        a.setName(division.getName() + " Alarm");

        Alarm a1 = new Alarm();
        a1.setOn(true);
        a1.setName(division1.getName() + " Alarm");

        Alarm a2 = new Alarm();
        a2.setOn(true);
        a2.setName(division2.getName() + " Alarm");

        Alarm a3 = new Alarm();
        a3.setOn(true);
        a3.setName(division3.getName() + " Alarm");

        Alarm a4 = new Alarm();
        a4.setOn(true);
        a4.setName(division4.getName() + " Alarm");

        Alarm a5 = new Alarm();
        a5.setOn(true);
        a5.setName(division5.getName() + " Alarm");

        Alarm a6 = new Alarm();
        a6.setOn(true);
        a6.setName(division6.getName() + " Alarm");


        alarmRepository.save(a);
        alarmRepository.save(a1);
        alarmRepository.save(a2);
        alarmRepository.save(a3);
        alarmRepository.save(a4);
        alarmRepository.save(a5);
        alarmRepository.save(a6);

        division.setAlarm(a);
        division1.setAlarm(a1);
        division2.setAlarm(a2);
        division3.setAlarm(a3);
        division4.setAlarm(a4);
        division5.setAlarm(a5);
        division6.setAlarm(a6);

        floorRepository.save(floor);
        floorRepository.save(floor1);

        divisionRepository.save(division);
        divisionRepository.save(division6);
        divisionRepository.save(division1);
        divisionRepository.save(division2);
        divisionRepository.save(division3);
        divisionRepository.save(division4);
        divisionRepository.save(division5);

        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }

    //Only for testes
    @GetMapping("/floor")
    public ResponseEntity<String> addFloor(@RequestHeader("Authorization") String token){


        List<Division> divisionList= divisionRepository.findAll();
        divisionList.size();

        List<Floor> floor = floorRepository.findAll();
        floor.forEach(System.out::println);

        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }

    @GetMapping("/new")
    public ResponseEntity<UserNewRequest> test(@RequestHeader("Authorization") String token){


        UserNewRequest userNewRequest = new UserNewRequest();
        UserDto u = UserDto.maptoDto(userRepository.findAll().stream().findFirst().get());
        userNewRequest.setUser(u);
        userNewRequest.setDivisions(u.getDivisions().stream().map(DivisionDto::getUuid).toList());
        userNewRequest.setDivisions(new ArrayList<>(Collections.singleton(UUID.randomUUID())));
        return new ResponseEntity<>(userNewRequest, HttpStatus.OK);
    }
}
