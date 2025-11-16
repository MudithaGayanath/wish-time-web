package lk.wishu.wish_time.controller;

import lk.wishu.wish_time.dto.response.PriorityResponse;
import lk.wishu.wish_time.service.PriorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/priority")
public class PriorityController {

    @Autowired
    @Lazy
    private PriorityService priorityService;

    @GetMapping(value = "/get/all")
    public ResponseEntity<List<PriorityResponse>> getAllPriorities(){
        return priorityService.getAll();
    }
}
