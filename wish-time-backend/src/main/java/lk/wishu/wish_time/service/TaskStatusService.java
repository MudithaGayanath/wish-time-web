package lk.wishu.wish_time.service;

import lk.wishu.wish_time.entity.TaskStatus;
import lk.wishu.wish_time.repository.TaskStatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskStatusService {
    public static final String  PENDING = "Pending";
    public static final String  IN_PROGRESS = "In Progress";
    public static final String  COMPLETED = "Completed";
    public static final String  SCHEDULED = "Scheduled";
    public static final String  IN_COMPLETE = "In Complete";


    @Autowired
    private TaskStatusRepo taskStatusRepo;

    protected TaskStatus getTaskStatusByName(String name){
        return this.taskStatusRepo.findByName(name).orElse(null);
    }

    protected boolean isValidId(int id){
        return this.taskStatusRepo.findById(id).isPresent();
    }
}
