package lk.wishu.wish_time.service;

import lk.wishu.wish_time.dto.request.TaskTypeRequest;
import lk.wishu.wish_time.dto.response.BaseResponse;
import lk.wishu.wish_time.dto.response.ErrorResponse;
import lk.wishu.wish_time.dto.response.TaskTypeResponse;
import lk.wishu.wish_time.entity.TaskType;
import lk.wishu.wish_time.repository.TaskTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class TaskTypeService {
    @Autowired
    private TaskTypeRepo taskTypeRepo;

    public ResponseEntity<List<TaskTypeResponse>> getAll() {
        List<TaskType> all = taskTypeRepo.findAll();
        if (!all.isEmpty()) {
        List<TaskTypeResponse> taskTypeResponses = new ArrayList<>();
            for (TaskType taskType : all) {
                TaskTypeResponse taskTypeResponse = new TaskTypeResponse();
                taskTypeResponse.setId(taskType.getId());
                taskTypeResponse.setName(taskType.getName());
                taskTypeResponses.add(taskTypeResponse);
            }
            System.out.println(200);
            return new ResponseEntity<>(taskTypeResponses, HttpStatus.OK);

        }
        System.out.println("404");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public TaskType getTaskTypeById(int id){
        return taskTypeRepo.findById(id).orElse(null);
    }

    /**
     * To insert new task type
     *
     * @param data
     * @return ResponseEntity<TaskTypeRequest>
     */
    public ResponseEntity<BaseResponse> insert(TaskTypeRequest data) {
        HashMap<String, String> errors = validate(data);
        if(errors.isEmpty()){
            TaskType taskType = new TaskType();
            taskType.setName(data.getName());
            taskTypeRepo.save(taskType);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(new ErrorResponse(errors), HttpStatus.BAD_REQUEST);
    }

    /**
     * To validate task type id
     * @param id
     * @return boolean
     */
    protected boolean  isValidId(int id){
        return taskTypeRepo.findById(id).isPresent();
    }

    private   HashMap<String, String> validate(TaskTypeRequest data) {
        HashMap<String, String> map = new HashMap<>();

        if (data.getName() == null || data.getName().isBlank()) {
            map.put("name", "Task type name required");
        }
        if (data.getName().length() > 45) {
            map.put("name", "Task type name can't be longer than 45 characters.");
        }

        if (taskTypeRepo.findByName(data.getName()).orElse(null) != null) {
            map.put("name", "Task type name already exists.");
        }

        return map;
    }
}
