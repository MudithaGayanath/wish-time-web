package lk.wishu.wish_time.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;

@Data
@Setter @Getter
public class SignInResponse {
    private String token;
    private HashMap<String,String> errors;
}
