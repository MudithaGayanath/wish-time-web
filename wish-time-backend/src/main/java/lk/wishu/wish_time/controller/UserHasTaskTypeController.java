package lk.wishu.wish_time.controller;

import lk.wishu.wish_time.dto.response.BaseResponse;
import lk.wishu.wish_time.dto.response.TaskTypeResponse;
import lk.wishu.wish_time.service.UserHasTaskTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/v1/userHasTaskType")
public class UserHasTaskTypeController {

    @Autowired
    @Lazy
    private UserHasTaskTypeService  userHasTaskTypeService;

    @GetMapping(value = "/get/all")
    public ResponseEntity<List<TaskTypeResponse>> getAll(@RequestHeader(value = "Authorization") String auth){
        return this.userHasTaskTypeService.getAll(auth.split(" ")[1]);
    }

    @PostMapping(value = "/post/{taskTypeId}")
    public ResponseEntity<BaseResponse> insert(@RequestHeader(value = "Authorization") String auth, @PathVariable String taskTypeId ) {
       return this.userHasTaskTypeService.insert(auth.split(" ")[1],taskTypeId);
    }
}
