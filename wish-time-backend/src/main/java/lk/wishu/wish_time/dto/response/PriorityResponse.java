package lk.wishu.wish_time.dto.response;

import lombok.*;

@Data
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class PriorityResponse implements BaseResponse{

    private int id;
    private String name;
}
