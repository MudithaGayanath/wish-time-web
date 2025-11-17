package lk.wishu.wish_time.service;

import lk.wishu.wish_time.dto.request.TaskRequest;
import lk.wishu.wish_time.repository.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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



    public void insert(String token, TaskRequest data){
        HashMap<String,String> errors = this.validate(data);
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

        return map;
    }
}
