package lk.wishu.service;

import lk.wishu.entity.UserStatus;
import lk.wishu.repository.UserStatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserStatusService {
    public static final String  ACTIVE = "Active";
    public static final String  INACTIVE = "Inactive";


    @Autowired
    public UserStatusRepo repo;

    public UserStatus getUserStatusByStatusName(String name){
       return repo.findByName(name).orElse(null);
    }
}
