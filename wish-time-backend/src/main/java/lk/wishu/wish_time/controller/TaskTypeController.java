package lk.wishu.wish_time.controller;


import lk.wishu.wish_time.dto.request.TaskTypeRequest;
import lk.wishu.wish_time.entity.TaskType;
import lk.wishu.wish_time.service.TaskTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/taskType")
public class TaskTypeController {
    @Autowired
    private TaskTypeService taskTypeService;
    
    @PostMapping
    @RequestMapping(value = "/post")
    public ResponseEntity<TaskTypeRequest> post(@RequestBody TaskTypeRequest data) throws Exception{
        return taskTypeService.insert(data);
    }
}
