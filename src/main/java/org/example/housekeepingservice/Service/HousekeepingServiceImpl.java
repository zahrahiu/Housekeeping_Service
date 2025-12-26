package org.example.housekeepingservice.Service;


import org.example.housekeepingservice.dto.RequestHousekeepingTaskDTO;
import org.example.housekeepingservice.dto.ResponseHousekeepingTaskDTO;
import org.example.housekeepingservice.Entity.HousekeepingTask;
import org.example.housekeepingservice.Mapper.HousekeepingTaskMapper;
import org.example.housekeepingservice.Repository.HousekeepingTaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HousekeepingServiceImpl implements HousekeepingService {

    private final HousekeepingTaskRepository repository;
    private final HousekeepingTaskMapper mapper;

    public HousekeepingServiceImpl(HousekeepingTaskRepository repository,
                                   HousekeepingTaskMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ResponseHousekeepingTaskDTO createTask(RequestHousekeepingTaskDTO dto) {
        HousekeepingTask task = mapper.DTO_to_Entity(dto);
        return mapper.Entity_to_DTO(repository.save(task));
    }

    @Override
    public List<ResponseHousekeepingTaskDTO> getAllTasks() {
        return repository.findAll()
                .stream()
                .map(mapper::Entity_to_DTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ResponseHousekeepingTaskDTO> getTasksByStatus(String status) {
        return repository.findByStatus(status)
                .stream()
                .map(mapper::Entity_to_DTO)
                .collect(Collectors.toList());
    }
}
