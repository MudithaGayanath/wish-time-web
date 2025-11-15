package lk.wishu.wish_time.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter @Getter
public class UserUpdateRequest {
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String email;
    private String statusId;
}
