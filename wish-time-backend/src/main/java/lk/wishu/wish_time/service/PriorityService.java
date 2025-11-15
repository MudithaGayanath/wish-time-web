package lk.wishu.wish_time.service;

import lk.wishu.wish_time.repository.PriorityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PriorityService {
    @Autowired
    private PriorityRepo priorityRepo;
}
