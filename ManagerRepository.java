package org.example.repository;

import org.example.DTO.ManagerDTO;
import org.example.repository.entity.ManagerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ManagerRepository {

    private NamedParameterJdbcTemplate template;

    @Autowired
    public ManagerRepository(NamedParameterJdbcTemplate t){
        this.template = t;
    }

    public List<ManagerEntity> getAllVisitors(){
        String query = "select * from visitors";
        Map<String, Object> params = new HashMap<>();
        RowMapper<ManagerEntity> rowMapper = new BeanPropertyRowMapper<>(ManagerEntity.class);
        return template.query(query, params, rowMapper);
    }

    public List<ManagerEntity> getVisitorsByStatus(String status){
        String query = "select * from visitors where visitor_status = :status";
        Map<String, Object> params = new HashMap<>();
        params.put("status", status);
        RowMapper<ManagerEntity> rowMapper = new BeanPropertyRowMapper<>(ManagerEntity.class);
        return template.query(query, params, rowMapper);
    }

    public ManagerEntity getVisitorById(int id){
        String query = "select * from visitors where visitor_id = :id";
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        RowMapper<ManagerEntity> rowMapper = new BeanPropertyRowMapper<>(ManagerEntity.class);
        return template.queryForObject(query, params, rowMapper);
    }

    //Once the Manager approves the visitor, the visitor can move in for the meeting
    public void approveVisitor(ManagerDTO managerDTO){
        String query = "update visitors SET visitor_status = 'approved' where visitor_id = :id";
        Map<String, Object> params = new HashMap<>();
        params.put("id",managerDTO.getVisitor_id());
        template.update(query, params);
    }

    //A Manager can also reject the purpose of the Visitor, then status will be updated to 'Rejected'
    public void rejectVisitor(ManagerDTO managerDTO){
        String query = "update visitors SET visitor_status = 'reject' where visitor_id = :id";
        Map<String, Object> params = new HashMap<>();
        params.put("id",managerDTO.getVisitor_id());
        template.update(query, params);
    }

    // Once the visitor leaves after the meeting is over, the status of the visitor will be 'Visit Completed"
    public void completeVisitor(ManagerDTO managerDTO){
        String query = "update visitors SET visitor_status = 'visit-complete' where visitor_id = :id and visitor_status = 'approved'";
        Map<String,Object> params = new HashMap<>();
        params.put("id", managerDTO.getVisitor_id());
        template.update(query, params);
    }
}
