package lk.wishu.wish_time.service;

import lk.wishu.wish_time.repository.TaskTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskTypeService {
    @Autowired
    private TaskTypeRepo taskTypeRepo;
}
