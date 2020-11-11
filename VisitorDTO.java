package org.example.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisitorDTO {

    private int visitor_id;
    private String visitor_name;
    private String visitor_status;
    private String purpose_of_meeting;
    private Timestamp meeting_time;
    private String manager_name;

}

