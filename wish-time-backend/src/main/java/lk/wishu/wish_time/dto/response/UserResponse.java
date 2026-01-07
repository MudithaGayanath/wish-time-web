package lk.wishu.wish_time.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserResponse implements BaseResponse {

    private String imageURL;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int statusId;

}
