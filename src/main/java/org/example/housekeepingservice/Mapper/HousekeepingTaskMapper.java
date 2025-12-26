package org.example.housekeepingservice.Mapper;


import org.example.housekeepingservice.Entity.HousekeepingTask;
import org.example.housekeepingservice.dto.RequestHousekeepingTaskDTO;
import org.example.housekeepingservice.dto.ResponseHousekeepingTaskDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class HousekeepingTaskMapper {

    public HousekeepingTask DTO_to_Entity(RequestHousekeepingTaskDTO requestDTO) {
        HousekeepingTask task = new HousekeepingTask();
        BeanUtils.copyProperties(requestDTO, task);
        return task;
    }

    public ResponseHousekeepingTaskDTO Entity_to_DTO(HousekeepingTask task) {
        ResponseHousekeepingTaskDTO responseDTO = new ResponseHousekeepingTaskDTO();
        BeanUtils.copyProperties(task, responseDTO);
        return responseDTO;
    }
}
