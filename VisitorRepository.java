package org.example.repository;

import org.example.DTO.VisitorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class VisitorRepository {

    private NamedParameterJdbcTemplate template;

    @Autowired
    public VisitorRepository(NamedParameterJdbcTemplate t){
        this.template = t;
    }

    //Adds new visitors to the visitor table with a default status "waiting"
    public void addNewVisitor(VisitorDTO visitorDTO){
        String query = "insert into visitors(visitor_name, visitor_status, meeting_time, purpose_of_meeting, manager_name) values (:name, :status, :time, :purpose, :manager_name)";
        Map<String, Object> params = new HashMap<>();
        params.put("name",visitorDTO.getVisitor_name());
        params.put("time",visitorDTO.getMeeting_time());
        params.put("purpose",visitorDTO.getPurpose_of_meeting());
        params.put("manager_name",visitorDTO.getManager_name());
        params.put("status","Waiting");
        template.update(query,params);
    }

}
