package lk.wishu.wish_time.repository;

import lk.wishu.wish_time.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {

    Optional<User> findByUserName(String username);
    Optional<User> findById(int id);
    Optional<User> findByEmail(String email);
}
