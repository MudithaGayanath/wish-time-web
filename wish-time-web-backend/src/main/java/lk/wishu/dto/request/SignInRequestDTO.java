package lk.wishu.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter @Setter
public class SignInRequestDTO {
private String username;
private String password;
}
