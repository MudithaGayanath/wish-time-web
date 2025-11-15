package lk.wishu.wish_time.repository;

import lk.wishu.wish_time.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepo extends JpaRepository<Task,Integer> {
}
