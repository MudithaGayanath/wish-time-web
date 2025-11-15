package lk.wishu.wish_time.service;

import lk.wishu.wish_time.repository.TaskStatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskStatusService {
    @Autowired
    private TaskStatusRepo taskStatusRepo;


}
