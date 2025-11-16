package lk.wishu.wish_time.dto.response;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter @Setter
public class TaskTypeResponse implements BaseResponse{
    private int id;
    private String name;
}
