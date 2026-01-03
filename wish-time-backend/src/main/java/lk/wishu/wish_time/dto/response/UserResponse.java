package lk.wishu.wish_time.dto.response;

import lombok.Data;

import java.util.Date;

@Data
public class UserResponse implements BaseResponse {

    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private Date createdAt;

}
