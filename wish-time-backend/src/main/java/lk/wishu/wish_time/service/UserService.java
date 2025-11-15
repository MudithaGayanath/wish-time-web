package lk.wishu.wish_time.service;


//import lk.wishu.dto.request.SignUpRequestDTO;
import lk.wishu.wish_time.dto.request.SignUpRequest;
import lk.wishu.wish_time.dto.request.UserUpdateRequest;
import lk.wishu.wish_time.dto.response.UserUpdateResponse;
import lk.wishu.wish_time.entity.User;
import lk.wishu.wish_time.repository.UserRepo;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private  UserRepo userRepo;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder ;
    @Autowired
    private UserStatusService userStatusService;

    public List<User> getUsers(){
        return userRepo.findAll();
    }


    /**
     *
     * @param  userName
     * @return User objec
     * @throws UsernameNotFoundException
     */
    public User getUserByUsername(String userName)  {
        System.out.println("getUserByUsername in user service "+userName);
        User user = userRepo.findByUserName(userName).orElse(null);
        if (user == null){
            System.out.println("user not found ");
            return null;
        }
        System.out.println(user.getEmail());
        return user;
    }

    public boolean getUser(String userName,String password) {
        User user = userRepo.findByUserName(userName).orElse(null);
        if (user == null){
            return false;
        }
        return true;

    }

    public void insert(SignUpRequest data){
        User user = new User();
        user.setUserName(data.getUserName());
        System.out.println(data.getPassword()+ " User password");
        user.setPassword(passwordEncoder.encode(data.getPassword()));
        user.setEmail(data.getEmail());
        user.setFirstName(data.getFirstName());
        user.setLastName(data.getLastName());
        user.setCreatedAt(LocalDateTime.now());
        user.setUserStatus(userStatusService.getUserStatusByStatusName(UserStatusService.ACTIVE));
        userRepo.save(user);
    }

    public UserUpdateResponse update(UserUpdateRequest data)throws HibernateException {
        User user = this.getUserByUsername(data.getUserName());
        user.setFirstName(data.getFirstName());
        user.setLastName(data.getLastName());
        user.setEmail(data.getEmail());
        user.setPassword(passwordEncoder.encode(data.getPassword()));
        user.setUpdatedAt(LocalDateTime.now());

        user.setUserStatus(userStatusService.getUserStatusById(Integer.parseInt(data.getStatusId())));

        user =  userRepo.save(user);
        UserUpdateResponse response = new UserUpdateResponse();
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setUserName(user.getUserName());
        response.setCreatedAt(String.valueOf(user.getCreatedAt()));
        response.setUpdatedAt(String.valueOf(user.getUpdatedAt()));


return response;
    }

    public User getUserByEmail(String email){
        return userRepo.findByEmail(email).orElse(null);
    }



    public User getUserById(int userId){
      return   userRepo.findById(userId).orElse(null);
    }
}
