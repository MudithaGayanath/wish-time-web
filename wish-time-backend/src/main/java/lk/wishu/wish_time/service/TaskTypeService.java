package lk.wishu.wish_time.service;

import lk.wishu.wish_time.dto.request.TaskTypeRequest;
import lk.wishu.wish_time.entity.TaskType;
import lk.wishu.wish_time.repository.TaskTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskTypeService {
    @Autowired
    private TaskTypeRepo taskTypeRepo;

    public List<TaskType> getAll() {
        return taskTypeRepo.findAll();
    }

    /**
     * To insert new task type
     * @param data
     * @return ResponseEntity<TaskTypeRequest>
     */
    public ResponseEntity<TaskTypeRequest> insert(TaskTypeRequest data){
    if(!isValid(data)){
    return  new ResponseEntity<>(data,HttpStatus.BAD_REQUEST);
    }else{
        TaskType taskType=new TaskType();
        taskType.setName(data.getName());
        taskTypeRepo.save(taskType);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    }

    public boolean isValid(TaskTypeRequest data){
        if(data.getName()==null || data.getName().isBlank()){
            data.setName("Task type name required");
            return false;
        }
        if(data.getName().length()> 45){
            data.setName("Task type name can't be longer than 45 characters.");
            return false;
        }

        if(taskTypeRepo.findByName(data.getName()).orElse(null) !=null){
            data.setName("Task type name already exists.");
            return false;
        }

        return true;
    }
}
