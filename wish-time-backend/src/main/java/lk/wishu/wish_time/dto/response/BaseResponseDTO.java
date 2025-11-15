package lk.wishu.wish_time.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Data
@Getter @Setter
public class BaseResponseDTO {
    private HashMap<String,String> errors;
}
