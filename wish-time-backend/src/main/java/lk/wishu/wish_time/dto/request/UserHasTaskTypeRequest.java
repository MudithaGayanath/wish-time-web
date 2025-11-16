package lk.wishu.wish_time.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter @Getter
public class UserHasTaskTypeRequest {
    private String userId;
    private String taskTypeId;
}
