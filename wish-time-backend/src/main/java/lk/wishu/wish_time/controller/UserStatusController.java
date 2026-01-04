package lk.wishu.wish_time.controller;

import lk.wishu.wish_time.dto.response.BaseResponse;
import lk.wishu.wish_time.service.UserStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/v1/userStatus")
public class UserStatusController {

    @Autowired
    private UserStatusService statusService;

    @GetMapping(value = "/getAll")
    public ResponseEntity<List<BaseResponse>> getAll() {
        return statusService.getAllUserStatus();

    }
}
