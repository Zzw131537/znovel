package com.zhouzw.znovel.core.auth;

// 作家后台管理 认证授权策略

import com.zhouzw.znovel.core.common.constant.ErrorCodeEnum;
import com.zhouzw.znovel.core.common.exception.BusinessException;
import com.zhouzw.znovel.core.constant.ApiRouterConsts;
import com.zhouzw.znovel.core.utils.JwtUtils;
import com.zhouzw.znovel.dao.entity.AuthorInfo;
import com.zhouzw.znovel.dto.AuthorInfoDto;
import com.zhouzw.znovel.manager.cache.AuthorInfoCacheManager;
import com.zhouzw.znovel.manager.cache.UserInfoCacheManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class AuthorAuthStrategy implements AuthStrategy {

    private final JwtUtils  jwtUtils;

    private final UserInfoCacheManager userInfoCacheManager;

    private final AuthorInfoCacheManager authorInfoCacheManager;

    // 不需要进行作家验证的URL
    private static final List<String> EXCLUDE_URL = List.of(
            ApiRouterConsts.API_AUTHOR_URL_PREFIX+"/register",
            ApiRouterConsts.API_AUTHOR_URL_PREFIX+"/status"
    );

    @Override
    public void auth(String token, String requestUrl) throws BusinessException {
        // 统一账号认证
        Long userId =authSSO(jwtUtils,userInfoCacheManager,token);

        if(EXCLUDE_URL.contains(requestUrl)){
            // 该请求不需要进行作家权限验证
            return ;
        }

        // 作家权限验证
        AuthorInfoDto authorInfoDto = authorInfoCacheManager.getAuthor(userId);

        if(Objects.isNull(authorInfoDto)){
            // 作家账号不存在，无权访问作家专区
            throw new BusinessException(ErrorCodeEnum.USER_UN_AUTH);
        }

        // 设置作家ID 到当前线程
        UserHolder.setAuthorId(authorInfoDto.getId());

    }
}
