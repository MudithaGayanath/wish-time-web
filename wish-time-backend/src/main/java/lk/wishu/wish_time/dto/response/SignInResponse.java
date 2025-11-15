package lk.wishu.wish_time.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class SignInResponse extends BaseResponseDTO {
    private String token;
}
