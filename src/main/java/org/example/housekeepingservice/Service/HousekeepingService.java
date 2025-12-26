package org.example.housekeepingservice.Service;


import org.example.housekeepingservice.dto.RequestHousekeepingTaskDTO;
import org.example.housekeepingservice.dto.ResponseHousekeepingTaskDTO;

import java.util.List;

public interface HousekeepingService {

    ResponseHousekeepingTaskDTO createTask(RequestHousekeepingTaskDTO dto);

    List<ResponseHousekeepingTaskDTO> getAllTasks();

    List<ResponseHousekeepingTaskDTO> getTasksByStatus(String status);
}

