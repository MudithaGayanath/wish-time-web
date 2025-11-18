package lk.wishu.wish_time.dto.response;

import java.time.LocalDate;

public class TaskResponse implements BaseResponse{
    private int id;
    private String title;
    private String description;
    private String estimatedTime;
    private LocalDate createdAt;
    private String taskType;
    private String priority;
}
