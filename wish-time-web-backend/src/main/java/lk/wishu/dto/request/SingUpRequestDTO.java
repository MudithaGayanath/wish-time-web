package lk.wishu.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter @Setter
public class SingUpRequestDTO {
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String email;
}
