package lk.wishu.wish_time.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter @Setter
public class TaskRequest {
    private String title;
    private String description;
    private String estimatedTime;
    private String taskTypeId;
//    private String statusId;
    private String priorityId;
}
