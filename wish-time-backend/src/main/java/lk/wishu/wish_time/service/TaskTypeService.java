package lk.wishu.wish_time.service;

import lk.wishu.wish_time.entity.TaskType;
import lk.wishu.wish_time.repository.TaskTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskTypeService {
    @Autowired
    private TaskTypeRepo taskTypeRepo;

    public List<TaskType> findAll() {
        return taskTypeRepo.findAll();
    }
}
