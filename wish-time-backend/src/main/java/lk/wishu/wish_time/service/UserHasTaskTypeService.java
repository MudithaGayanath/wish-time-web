package lk.wishu.wish_time.service;

import lk.wishu.wish_time.dto.request.TaskTypeRequest;
import lk.wishu.wish_time.dto.request.UserHasTaskTypeRequest;
import lk.wishu.wish_time.dto.response.BaseResponse;
import lk.wishu.wish_time.entity.UserHasTaskType;
import lk.wishu.wish_time.repository.UserHasTaskTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;

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
        return this.userHasTaskTypeRepo.findUserHasTaskTypeByUserIdAndTaskTypeId(userId, taskTypeId).orElse(null) != null;
    }



}
