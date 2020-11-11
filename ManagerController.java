package org.example.controller;

import org.example.DTO.ManagerDTO;
import org.example.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manager")
public class ManagerController {

    private ManagerService mService;

    @Autowired
    public ManagerController(ManagerService m) {
        this.mService = m;
    }

    //Get all the visitors that is saved in the database
    @GetMapping(produces = "application/json")
    public ResponseEntity getAllVisitors(){
        return ResponseEntity.ok(mService.getAllVisitors());
    }

    //Get the details of a particular visitor based on visitor id
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity getVisitorById(@PathVariable int id) {
        return ResponseEntity.ok(mService.getVisitorById(id));
    }

    //View all the visitor based on some status given in the input path
    @GetMapping(value = "/status/{status}", produces = "application/json")
    public ResponseEntity getVisitorsByStatus(@PathVariable String status) {
        return ResponseEntity.ok(mService.getVisitorsByStatus(status));
    }

    //A Manager can approve a visitor and will be saved on the database with the status "approved"
    @PostMapping(value = "/approve/{id}", consumes = "application/json")
    public ResponseEntity approveVisitor(@RequestBody ManagerDTO managerDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mService.approveVisitor(managerDTO));
    }

    @PostMapping(value = "/reject/{id}", consumes = "application/json")
    public ResponseEntity rejectVisitor(@RequestBody ManagerDTO managerDTO) {
        return ResponseEntity.ok(mService.rejectVisitor(managerDTO));
    }

    @PostMapping(value = "/complete/{id}", consumes = "application/json")
    public ResponseEntity completeVisitor(@RequestBody ManagerDTO managerDTO){
        return ResponseEntity.ok(mService.completeVisitor(managerDTO));
    }
}
