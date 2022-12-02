package com.isec.gps41.SmartSecurity.controllers.frontoffice;

import com.isec.gps41.SmartSecurity.exception.ResourcesInvalid;
import com.isec.gps41.SmartSecurity.payload.ListUUID;
import com.isec.gps41.SmartSecurity.payload.ResultOfDesativeAtiveAlarms;
import com.isec.gps41.SmartSecurity.payload.division.DivisionDto;
import com.isec.gps41.SmartSecurity.service.AlarmManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fo/divisions")
public class DivisionController {

    @Autowired
    AlarmManagementService alarmService;

    @GetMapping()
    public ResponseEntity<String> index(@RequestHeader("Authorization") String authHeader){


        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }


    @GetMapping("/leave")
    public ResponseEntity<String> leave(@RequestHeader("Authorization") String authHeader){
        String token = authHeader.substring(7);
        alarmService.leave(token);
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @PostMapping("/goto")
    public ResponseEntity<String> goTo(@RequestHeader("Authorization") String authHeader,
                                       @RequestBody ListUUID listDivisionUUID){
        String token = authHeader.substring(7);
        alarmService.goTo(token, listDivisionUUID.getUuids().stream().findFirst());
        return new ResponseEntity<>("", HttpStatus.OK);
    }



}
