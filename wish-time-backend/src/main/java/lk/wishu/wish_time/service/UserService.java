package lk.wishu.wish_time.service;


//import lk.wishu.dto.request.SignUpRequestDTO;
import lk.wishu.wish_time.dto.request.SignUpRequestDTO;
import lk.wishu.wish_time.entity.User;
import lk.wishu.wish_time.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
    public User getUserByUsername(String userName) throws UsernameNotFoundException {
        System.out.println("getUserByUsername in user service "+userName);
        User user = userRepo.findByUserName(userName).orElse(null);
        if (user == null){
            System.out.println("user not found ");
            throw new UsernameNotFoundException("No user found with username: " + userName);
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

    public void insert(SignUpRequestDTO data){
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

    public User getUserByEmail(String email){
        return userRepo.findByEmail(email).orElse(null);
    }

    public User getUserById(int userId){
      return   userRepo.findById(userId).orElse(null);
    }
}
