package lk.wishu.wish_time.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Getter @Setter
public class TaskResponse implements BaseResponse{
    private int id;
    private String title;
    private String description;
    private String estimatedTime;
    private LocalDate createdAt;
    private String taskType;
    private String priority;
}
