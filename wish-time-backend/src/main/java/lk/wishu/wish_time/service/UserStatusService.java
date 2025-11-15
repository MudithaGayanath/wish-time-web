package lk.wishu.wish_time.service;

import lk.wishu.wish_time.entity.UserStatus;
import lk.wishu.wish_time.repository.UserStatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserStatusService {
    public static final String  ACTIVE = "Active";
    public static final String  INACTIVE = "Inactive";


    @Autowired
    public UserStatusRepo repo;

    public UserStatus getUserStatusByStatusName(String name){
        return repo.findByName(name).orElse(null);
    }

    public List<UserStatus> getAllUserStatus(){
        return repo.findAll();
    }

    public UserStatus getUserStatusById(int id){
        return repo.findById(id).orElse(null);
    }
}
