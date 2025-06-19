package com.zhouzw.znovel.dto.resp;


import lombok.Builder;
import lombok.Data;

/**
 * 用户登录响应 DTO
 */
@Data
@Builder
public class UserLoginRespDto {
    private Long uid;

    private String nickname;

    private String token;
}
