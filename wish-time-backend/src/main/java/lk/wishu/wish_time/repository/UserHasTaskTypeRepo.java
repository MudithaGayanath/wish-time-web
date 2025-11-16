package lk.wishu.wish_time.repository;

import lk.wishu.wish_time.entity.UserHasTaskType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserHasTaskTypeRepo extends JpaRepository<UserHasTaskType,Integer> {
    Optional<UserHasTaskType> findUserHasTaskTypeByUserIdAndTaskTypeId(Integer userId,Integer taskTypeId);
}
