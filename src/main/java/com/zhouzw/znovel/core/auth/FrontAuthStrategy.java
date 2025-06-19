package com.zhouzw.znovel.core.auth;


import com.zhouzw.znovel.core.auth.AuthStrategy;
import com.zhouzw.znovel.core.common.exception.BusinessException;
import com.zhouzw.znovel.core.utils.JwtUtils;
import com.zhouzw.znovel.manager.cache.UserInfoCacheManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 前台门户系统 认证授权策略
 */
@Component
@RequiredArgsConstructor
public class FrontAuthStrategy implements AuthStrategy {

    private final JwtUtils jwtUtils;

    private final UserInfoCacheManager userInfoCacheManager;

    @Override
    public void auth(String token, String requestUri) throws BusinessException {
        // 统一账号认证
        authSSO(jwtUtils, userInfoCacheManager, token);
    }

}