package lk.wishu.wish_time.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@Data
@Getter @Setter
public class TaskScheduleResponse {
    private HashMap<LocalDate, List<TaskResponse>> tasks;
}
