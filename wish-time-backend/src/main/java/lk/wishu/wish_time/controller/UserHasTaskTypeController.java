package lk.wishu.wish_time.controller;

import lk.wishu.wish_time.dto.response.BaseResponse;
import lk.wishu.wish_time.service.UserHasTaskTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/userHasTaskType")
public class UserHasTaskTypeController {

    @Autowired
    @Lazy
    private UserHasTaskTypeService  userHasTaskTypeService;

    @PostMapping(value = "/post/{taskTypeId}")
    public ResponseEntity<BaseResponse> insert(@RequestHeader(value = "Authorization") String auth, @PathVariable String taskTypeId ) {
        System.out.println(auth);
        System.out.println(taskTypeId);
        return this.userHasTaskTypeService.insert(auth.split(" ")[1],taskTypeId);
    }
}
