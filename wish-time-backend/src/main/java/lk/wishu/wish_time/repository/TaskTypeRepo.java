package lk.wishu.wish_time.repository;

import lk.wishu.wish_time.entity.TaskType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskTypeRepo extends JpaRepository<TaskType,Integer> {

    public Optional<TaskType> findByName(String name);

}
