package lk.wishu.wish_time.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter @Getter
public class SignInResponse implements BaseResponse {
    private String token;
}
