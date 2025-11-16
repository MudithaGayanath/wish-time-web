package lk.wishu.wish_time.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

@Data
@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse implements BaseResponse{
    private HashMap<String,String> errors;
}
