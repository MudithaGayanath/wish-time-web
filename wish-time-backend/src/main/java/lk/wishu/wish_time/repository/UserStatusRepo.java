package lk.wishu.wish_time.repository;

import lk.wishu.wish_time.entity.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserStatusRepo extends JpaRepository<UserStatus,Integer> {
     Optional<UserStatus> findByName(String name);
}
