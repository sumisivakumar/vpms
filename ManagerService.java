package org.example.service;

import org.example.DTO.ManagerDTO;
import org.example.repository.ManagerRepository;
import org.example.repository.entity.ManagerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ManagerService {

    private ManagerRepository mRepository;

    @Autowired
    public ManagerService(ManagerRepository m) {
        this.mRepository = m;
    }

    public List<ManagerDTO> getAllVisitors() {
       List<ManagerEntity> mEntities = mRepository.getAllVisitors();
       List<ManagerDTO> returnVal = new ArrayList<>();
       for(ManagerEntity m: mEntities) {
           ManagerDTO managerLists = new ManagerDTO(m.getVisitor_id(), m.getVisitor_name(), m.getVisitor_status(), m.getPurpose_of_meeting(), m.getMeeting_time(), m.getManager_name());
           returnVal.add(managerLists);
       }
       return returnVal;
    }

    public ManagerDTO getVisitorById(int id){
        ManagerEntity entity = mRepository.getVisitorById(id);
        return  new ManagerDTO(entity.getVisitor_id(),
                                entity.getVisitor_name(),
                                entity.getVisitor_status(),
                                entity.getPurpose_of_meeting(),
                                entity.getMeeting_time(),
                                entity.getManager_name());
    }

    public List<ManagerDTO> getVisitorsByStatus (String status){
        List<ManagerEntity> mEntities = mRepository.getVisitorsByStatus(status);
        List<ManagerDTO> returnVal = new ArrayList<>();
        for(ManagerEntity m: mEntities) {
            ManagerDTO managerLists = new ManagerDTO(m.getVisitor_id(),
                                                    m.getVisitor_name(),
                                                    m.getVisitor_status(),
                                                    m.getPurpose_of_meeting(),
                                                    m.getMeeting_time(),
                                                    m.getManager_name());
            returnVal.add(managerLists);
        }
        return returnVal;
    }

    public ManagerDTO approveVisitor(ManagerDTO managerDTO) {
        mRepository.approveVisitor(managerDTO);
        return managerDTO;
    }

    public ManagerDTO rejectVisitor(ManagerDTO managerDTO) {
        mRepository.rejectVisitor(managerDTO);
        return managerDTO;
    }

    public ManagerDTO completeVisitor(ManagerDTO managerDTO) {
        mRepository.completeVisitor(managerDTO);
        return managerDTO;
    }
}
