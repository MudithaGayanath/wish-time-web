package lk.wishu.wish_time.dto.response;

import lombok.Data;

@Data
public class UserStatusResponse implements BaseResponse{
    private int id;
    private String name;
}
