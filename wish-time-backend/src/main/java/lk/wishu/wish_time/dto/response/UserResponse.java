package lk.wishu.wish_time.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class UserResponse implements BaseResponse {

    private String imageURL;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int statusId;

}
