package lk.wishu.wish_time.service;

import lk.wishu.wish_time.dto.response.BaseResponse;
import lk.wishu.wish_time.dto.response.UserStatusResponse;
import lk.wishu.wish_time.entity.UserStatus;
import lk.wishu.wish_time.repository.UserStatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public ResponseEntity<List<BaseResponse>> getAllUserStatus(){
        List<UserStatus> all = repo.findAll();
        if(all.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<UserStatusResponse> arr = new ArrayList<>();
        for (UserStatus userStatus : all) {
            UserStatusResponse obj =  new UserStatusResponse();
            obj.setId(userStatus.getId());
            obj.setName(userStatus.getName());
            arr.add(obj);
        }
        return  new ResponseEntity(arr,HttpStatus.OK);
    }

    public UserStatus getUserStatusById(int id){
        return repo.findById(id).orElse(null);
    }
}
