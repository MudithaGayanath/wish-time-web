package lk.wishu.wish_time.service;

import lk.wishu.wish_time.dto.request.TaskRequest;
import lk.wishu.wish_time.dto.response.BaseResponse;
import lk.wishu.wish_time.dto.response.ErrorResponse;
import lk.wishu.wish_time.repository.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class TaskService {
    @Autowired
    @Lazy
    private TaskRepo taskRepo;
    @Autowired
    @Lazy
    private JWTService jwtService;
    @Autowired
    @Lazy
    private UserService userService;
    @Autowired
    @Lazy
    private TaskTypeService taskTypeService;
    @Autowired
    @Lazy
    private TaskStatusService taskStatusService;
    @Autowired
    @Lazy
    private PriorityService priorityService;



    public ResponseEntity<BaseResponse> insert(String token, TaskRequest data){
        HashMap<String,String> errors = this.validate(data);
        if(!errors.isEmpty()){
          return   new ResponseEntity(new ErrorResponse(errors), HttpStatus.BAD_REQUEST);
        }
        return  new ResponseEntity<>(HttpStatus.OK);
    }

    private HashMap<String, String> validate(TaskRequest data) {
        HashMap<String, String> map = new HashMap<>();
//        validate title
        if(data.getTitle() == null || data.getTitle().isBlank()){
            map.put("title","Title required");
        } else if (data.getTitle().length() > 45) {
            map.put("title","Title can't be longer than 45 characters");
        }

//        validate description
        if(data.getDescription() != null || !data.getDescription().isBlank()){
            if(data.getDescription().length() > 500){
                map.put("description","Description can't be longer than 500 characters");
            }
        }

//        validate estimated time
        if(data.getEstimatedTime() == null || data.getEstimatedTime().isBlank()){
            map.put("estimatedTime","Estimated time required");
        }else {
            try {
                if(Double.parseDouble(data.getEstimatedTime()) <= 0){
                    map.put("estimatedTime","Estimated time must be greater than 0");
                }
            } catch (NumberFormatException e) {
                map.put("estimatedTime","Estimated time must be a number");
            }
        }
// validate task id
        if(data.getTaskTypeId() == null || data.getTaskTypeId().isBlank()){
            map.put("taskTypeId","Task type required");
        }else{
            try {
                if(!taskTypeService.isValidId(Integer.parseInt(data.getTaskTypeId()))){
                    map.put("taskTypeId","Invalid task type");
                }
            }catch (NumberFormatException e){
                map.put("taskTypeId","Task type must be a number");
            }
        }

        // validate task status id
        if(data.getStatusId() == null || data.getStatusId().isBlank()){
            map.put("statusId","Status required");
        }else {
            try {
                if(!taskStatusService.isValidId(Integer.parseInt(data.getStatusId()))){
                    map.put("statusId","Invalid status");
                }
            }catch (NumberFormatException e){
                map.put("statusId","Status must be a number");
            }
        }

//        validate priority id
        if(data.getPriorityId() == null || data.getPriorityId().isBlank()){
            map.put("priorityId","Priority required");
        }else{
            try {
                if(!priorityService.isValidId(Integer.parseInt(data.getPriorityId()))){
                    map.put("priorityId","Invalid priority");
                }
            }catch (NumberFormatException e){
                map.put("priorityId","Priority must be a number");
            }
        }

        return map;
    }
}
