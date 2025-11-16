package lk.wishu.wish_time.controller;


import lk.wishu.wish_time.dto.request.TaskTypeRequest;
import lk.wishu.wish_time.dto.response.BaseResponse;
import lk.wishu.wish_time.dto.response.TaskTypeResponse;
import lk.wishu.wish_time.entity.TaskType;
import lk.wishu.wish_time.service.TaskTypeService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/taskType")
public class TaskTypeController {
    @Autowired
    private TaskTypeService taskTypeService;
    
    @GetMapping
    @RequestMapping(value = "/get/all")
    public ResponseEntity<List<TaskTypeResponse>> getAllTaskType(){
        return  taskTypeService.getAll();
    }
    
    @PostMapping
    @RequestMapping(value = "/post")
    public ResponseEntity<BaseResponse> post(@RequestBody TaskTypeRequest data) throws Exception{
        return taskTypeService.insert(data);
    }
    
    
}
