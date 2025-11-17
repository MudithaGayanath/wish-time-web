package lk.wishu.wish_time.service;

import lk.wishu.wish_time.dto.response.BaseResponse;
import lk.wishu.wish_time.dto.response.TaskTypeResponse;
import lk.wishu.wish_time.entity.UserHasTaskType;
import lk.wishu.wish_time.repository.UserHasTaskTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class UserHasTaskTypeService {
    @Autowired
    @Lazy
    private UserHasTaskTypeRepo userHasTaskTypeRepo;

    @Autowired
    @Lazy
    private TaskTypeService taskTypeService;

    @Autowired
    @Lazy
    private UserService userService;

    @Autowired
    @Lazy
    private JWTService jwtService;

    public ResponseEntity<BaseResponse> insert(String token, String taskTypeId) {
        HashMap<String, String> errors = this.validate(token, taskTypeId);
        if (!errors.isEmpty()){
         return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
        }
        UserHasTaskType type = new UserHasTaskType();
        try {
            type.setUser(userService.getUserByUsername(jwtService.getUsername(token)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        type.setTaskType(taskTypeService.getTaskTypeById(Integer.parseInt(taskTypeId)));

        this.userHasTaskTypeRepo.save(type);

        return new ResponseEntity(HttpStatus.OK);
    }

    public ResponseEntity<List<TaskTypeResponse>> getAll(String token){
        try {
            Optional<List<UserHasTaskType>> list = this.userHasTaskTypeRepo.findByUserId(userService.getUserByUsername(jwtService.getUsername(token)).getId());
            if(list.isEmpty()){
               return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            List<TaskTypeResponse> array = new ArrayList<>();
            for(UserHasTaskType userHasTaskType : list.get()){
                TaskTypeResponse taskType = new TaskTypeResponse();
                taskType.setId(userHasTaskType.getTaskType().getId());
                taskType.setName(userHasTaskType.getTaskType().getName());
                array.add(taskType);
            }
           return new ResponseEntity<>(array, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private HashMap<String, String> validate(String token, String taskTypeId) {
        HashMap<String, String> map = new HashMap<>();
        try {
            if (taskTypeId == null || taskTypeId.isBlank()) {
                map.put("taskTypeId", "Task type id required");
            } else if (Integer.parseInt(taskTypeId) <= 0 || !taskTypeService.isValidId(Integer.parseInt(taskTypeId))) {
                map.put("taskTypeId", "Invalid task type id");
            } else if (this.isAlreadyExists(userService.getUserByUsername(jwtService.getUsername(token)).getId(), Integer.parseInt(taskTypeId))) {
                map.put("taskTypeId", "Already exists");
            }
        } catch (NumberFormatException e) {
            map.put("taskTypeId", "Task type id must be a number");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

    private boolean isAlreadyExists(Integer userId, int taskTypeId) {
        return this.userHasTaskTypeRepo.findByUserIdAndTaskTypeId(userId, taskTypeId).orElse(null) != null;
    }



}
