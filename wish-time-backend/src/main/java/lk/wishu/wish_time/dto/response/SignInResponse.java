package lk.wishu.wish_time.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Setter @Getter
public class SignInResponse {
    private String token;
    private List<String> errors;
}
