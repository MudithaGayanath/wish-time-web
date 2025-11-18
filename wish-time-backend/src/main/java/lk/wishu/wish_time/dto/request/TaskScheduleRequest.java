package lk.wishu.wish_time.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class TaskScheduleRequest {
    private List<TaskRequest> tasks;
    private String date;
}
