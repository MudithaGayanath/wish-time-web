package lk.wishu.wish_time.controller;

import lk.wishu.wish_time.dto.request.TaskRequest;
import lk.wishu.wish_time.dto.response.BaseResponse;
import lk.wishu.wish_time.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/v1/task")
public class TaskController {
    @Autowired
    @Lazy
    private TaskService taskService;

    @PostMapping(value = "/post/today")
    public ResponseEntity<BaseResponse> postToday(@RequestHeader(value = "Authorization") String auth, @RequestBody TaskRequest data){
        return this.taskService.insertToday(auth.split(" ")[1],data);
    }
}
