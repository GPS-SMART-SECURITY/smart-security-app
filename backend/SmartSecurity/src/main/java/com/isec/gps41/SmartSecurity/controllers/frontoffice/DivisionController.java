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
@RequestMapping("/api/fo/division")
public class DivisionController {

    @Autowired
    AlarmManagementService alarmManagementService;

    @GetMapping("/leave")
    public ResponseEntity<String> leave(@RequestHeader("Authorization") String authHeader){
        String token = authHeader.substring(7);
        alarmManagementService.leave(token);
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @PostMapping("/goto")
    public ResponseEntity<String> goTo(@RequestHeader("Authorization") String authHeader,
                                       @RequestBody ListUUID listDivisionUUID){
        String token = authHeader.substring(7);
        alarmManagementService.goTo(token, listDivisionUUID.getUuids().stream().findFirst());
        return new ResponseEntity<>("", HttpStatus.OK);
    }



}
