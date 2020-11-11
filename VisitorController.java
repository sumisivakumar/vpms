package org.example.controller;

import org.example.DTO.VisitorDTO;
import org.example.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/visitors")
public class VisitorController {

    private VisitorService service;

    @Autowired
    public VisitorController(VisitorService s){
        this.service = s;
    }

    //Add new visitors to the visitor database
    @PostMapping(consumes = "application/json")
    public ResponseEntity addNewVisitor(@RequestBody VisitorDTO visitorDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addNewVisitor(visitorDTO));
    }

}
