package com.zhouzw.znovel.dto.resp;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInfoRespDto {

    private String nickname;

    private String userPhoto;

    private Integer userSex;
}
