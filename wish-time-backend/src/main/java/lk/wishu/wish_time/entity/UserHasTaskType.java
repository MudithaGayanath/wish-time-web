package lk.wishu.wish_time.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_has_task_type")
@Getter
@Setter
public class UserHasTaskType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @JoinColumn(name = "user_id",nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(name = "task_type_id",nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private TaskType taskType;
}
