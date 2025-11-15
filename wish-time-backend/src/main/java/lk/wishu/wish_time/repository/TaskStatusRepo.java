package lk.wishu.wish_time.repository;

import lk.wishu.wish_time.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskStatusRepo extends JpaRepository<TaskStatus,Integer> {

}
