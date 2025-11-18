package lk.wishu.wish_time.service;

import lk.wishu.wish_time.dto.request.TaskRequest;
import lk.wishu.wish_time.dto.request.TaskScheduleRequest;
import lk.wishu.wish_time.dto.response.BaseResponse;
import lk.wishu.wish_time.dto.response.ErrorResponse;
import lk.wishu.wish_time.entity.Task;
import lk.wishu.wish_time.entity.User;
import lk.wishu.wish_time.repository.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.HashMap;

@Service
public class TaskService {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Autowired
    @Lazy
    private TaskRepo taskRepo;
    @Autowired
    @Lazy
    private JWTService jwtService;
    @Autowired
    @Lazy
    private UserService userService;
    @Autowired
    @Lazy
    private TaskTypeService taskTypeService;
    @Autowired
    @Lazy
    private TaskStatusService taskStatusService;
    @Autowired
    @Lazy
    private PriorityService priorityService;
    @Autowired
    @Lazy
    private UserHasTaskTypeService userHasTaskTypeService;


    public ResponseEntity<BaseResponse> insertToday(String token, TaskRequest data) {

//        HashMap<String, String> errors = this.validateTasksRequest(data);
//        if (!errors.isEmpty()) {
//            return new ResponseEntity(new ErrorResponse(errors), HttpStatus.BAD_REQUEST);
//        }
//        Task task = new Task();
//        task.setTitle(data.getTitle());
//        task.setDescription(data.getDescription());
//        task.setEstimatedTime(Double.parseDouble(data.getEstimatedTime()));
//        task.setCreateAt(LocalDate.now());
//        task.setTaskStatus(taskStatusService.getTaskStatusByName(TaskStatusService.PENDING));
//        task.setPriority(priorityService.getById(Integer.parseInt(data.getPriorityId())));
//        taskRepo.save(task);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private HashMap<String, String> validateTasksRequest(TaskRequest data, int userId) {
        HashMap<String, String> map = new HashMap<>();
//        validateTasksRequest title
        if (data.getTitle() == null || data.getTitle().isBlank()) {
            map.put("title", "Title required");
        } else if (data.getTitle().length() > 45) {
            map.put("title", "Title can't be longer than 45 characters");
        }

//        validateTasksRequest description
        if (data.getDescription() != null || !data.getDescription().isBlank()) {
            if (data.getDescription().length() > 500) {
                map.put("description", "Description can't be longer than 500 characters");
            }
        }

//        validateTasksRequest estimated time
        if (data.getEstimatedTime() == null || data.getEstimatedTime().isBlank()) {
            map.put("estimatedTime", "Estimated time required");
        } else {
            try {
                if (Double.parseDouble(data.getEstimatedTime()) <= 0) {
                    map.put("estimatedTime", "Estimated time must be greater than 0");
                }
            } catch (NumberFormatException e) {
                map.put("estimatedTime", "Estimated time must be a number");
            }
        }
// validateTasksRequest task id
        if (data.getTaskTypeId() == null || data.getTaskTypeId().isBlank()) {
            map.put("taskTypeId", "Task type required");
        } else {
            try {
                if (!taskTypeService.isValidId(Integer.parseInt(data.getTaskTypeId()))) {
                    map.put("taskTypeId", "Invalid task type");
                } else if (!userHasTaskTypeService.isHave(userId, Integer.parseInt(data.getTaskTypeId()))) {
                    map.put("taskTypeId", "Not registered task type");
                }

            } catch (NumberFormatException e) {
                map.put("taskTypeId", "Task type must be a number");
            }
        }

        // validateTasksRequest task status id
//        if(data.getStatusId() == null || data.getStatusId().isBlank()){
//            map.put("statusId","Status required");
//        }else {
//            try {
//                if(!taskStatusService.isValidId(Integer.parseInt(data.getStatusId()))){
//                    map.put("statusId","Invalid status");
//                }
//            }catch (NumberFormatException e){
//                map.put("statusId","Status must be a number");
//            }
//        }

//        validateTasksRequest priority id
        if (data.getPriorityId() == null || data.getPriorityId().isBlank()) {
            map.put("priorityId", "Priority required");
        } else {
            try {
                if (!priorityService.isValidId(Integer.parseInt(data.getPriorityId()))) {
                    map.put("priorityId", "Invalid priority");
                }
            } catch (NumberFormatException e) {
                map.put("priorityId", "Priority must be a number");
            }
        }


        return map;
    }

    private String validateDate(String date) {
        if (date == null || date.isBlank()) {
            return "Date required";
        } else {
            try {
                LocalDate parseDate = LocalDate.parse(date, DATE_FORMATTER);
//                LocalDate today = ;
                if (parseDate.isBefore(LocalDate.now())) {
                    return "Date can't be in the past";
                }
            } catch (DateTimeParseException e) {
                return "Invalid date format";
            }
        }
        return null;
    }

    public ResponseEntity<BaseResponse> scheduleTasks(String token, TaskScheduleRequest data) throws Exception {

        if (this.validateDate(data.getDate()) != null) {
            HashMap<String, String> dateError = new HashMap<>();
            dateError.put("date", this.validateDate(data.getDate()));
            return new ResponseEntity<>(new ErrorResponse(dateError), HttpStatus.BAD_REQUEST);
        }
        User user = userService.getUserByUsername(jwtService.getUsername(token));
        for (int i = 0; i < data.getTasks().size(); i++) {
            HashMap<String, String> taskerrors = this.validateTasksRequest(data.getTasks().get(i), user.getId());
            if (!taskerrors.isEmpty()) {
                taskerrors.put("index", String.valueOf(i));
                return new ResponseEntity<BaseResponse>(new ErrorResponse(taskerrors), HttpStatus.BAD_REQUEST);
            }
        }

        for (TaskRequest taskRequest : data.getTasks()) {
            Task task = new Task();
            task.setTitle(taskRequest.getTitle());
            task.setDescription(taskRequest.getDescription());
            task.setEstimatedTime(Double.parseDouble(taskRequest.getEstimatedTime()));
            task.setCreateAt(LocalDate.parse(data.getDate(), DATE_FORMATTER));
            task.setUserHasTaskType(userHasTaskTypeService.getUserHasTaskType(user.getId(),Integer.parseInt(taskRequest.getTaskTypeId())));
            task.setTaskStatus(taskStatusService.getTaskStatusByName(TaskStatusService.SCHEDULED));
            task.setPriority(priorityService.getById(Integer.parseInt(taskRequest.getPriorityId())));
            taskRepo.save(task);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
