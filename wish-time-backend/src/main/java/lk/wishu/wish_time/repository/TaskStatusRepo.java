package lk.wishu.wish_time.repository;

import lk.wishu.wish_time.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskStatusRepo extends JpaRepository<TaskStatus, Integer> {
    Optional<TaskStatus> findByName(String name);
}
