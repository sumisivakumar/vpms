package org.example.service;

import org.example.DTO.VisitorDTO;
import org.example.repository.VisitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisitorService {

    private VisitorRepository repository;

    @Autowired
    public VisitorService(VisitorRepository r){
        this.repository = r;
    }

    public VisitorDTO addNewVisitor(VisitorDTO visitorDTO){
        repository.addNewVisitor(visitorDTO);
        return visitorDTO;
    }

}
