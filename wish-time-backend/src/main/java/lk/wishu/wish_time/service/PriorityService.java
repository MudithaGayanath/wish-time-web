package lk.wishu.wish_time.service;

import lk.wishu.wish_time.entity.Priority;
import lk.wishu.wish_time.repository.PriorityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriorityService {

    private static final String HIGH = "High";
    private static final String MEDIUM = "Medium";
    private static final String LOW = "Low";

    @Autowired
    private PriorityRepo priorityRepo;

    public List<Priority> getAll() {
        return priorityRepo.findAll();
    }

    public Priority getByName(String name){
        return priorityRepo.findByNama(name).orElse(null);
    }
}
