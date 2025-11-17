package lk.wishu.wish_time.service;

import lk.wishu.wish_time.dto.response.PriorityResponse;
import lk.wishu.wish_time.entity.Priority;
import lk.wishu.wish_time.repository.PriorityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PriorityService {

    private static final String HIGH = "High";
    private static final String MEDIUM = "Medium";
    private static final String LOW = "Low";

    @Autowired
    private PriorityRepo priorityRepo;

    /**
     *  To get all priorities
     * @return List<Priority>
     */
    public ResponseEntity<List<PriorityResponse>> getAll() {
        List<Priority> all = priorityRepo.findAll();
        if(!all.isEmpty()){
            List<PriorityResponse> priorities = new ArrayList<>();
            for(Priority priority:all){
                priorities.add(new PriorityResponse(priority.getId(),priority.getName()));
            }
            return  ResponseEntity.ok(priorities);
        }
        return  ResponseEntity.notFound().build();
    }

    /**
     *
     * @param name of priority
     * @return Priority or Null
     */
    public Priority getByName(String name){
        return priorityRepo.findByName(name).orElse(null);
    }

    protected boolean isValidId(int id){
        return this.priorityRepo.findById(id).isPresent();
    }

}
