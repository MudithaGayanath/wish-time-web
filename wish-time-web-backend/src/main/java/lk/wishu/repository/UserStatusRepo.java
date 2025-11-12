package lk.wishu.repository;

import lk.wishu.entity.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserStatusRepo extends JpaRepository<UserStatus,Integer> {
    Optional<UserStatus> findByName(String name);
}
