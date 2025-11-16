package lk.wishu.wish_time.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserUpdateResponse implements BaseResponse {
    private Integer id;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String email;
    private String createdAt;
    private String updatedAt;
    private String status;

}
