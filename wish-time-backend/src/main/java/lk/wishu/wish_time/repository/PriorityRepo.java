package lk.wishu.wish_time.repository;

import lk.wishu.wish_time.entity.Priority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PriorityRepo extends JpaRepository<Priority,Integer> {

    Optional<Priority> findByNama(String name);
}
