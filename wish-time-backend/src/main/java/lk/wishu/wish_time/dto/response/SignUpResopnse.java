package lk.wishu.wish_time.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Data
@Setter @Getter
public class SignUpResopnse {
    private HashMap<String,String> errors;
}
