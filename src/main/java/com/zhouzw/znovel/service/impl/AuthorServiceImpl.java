package com.zhouzw.znovel.service.impl;


import com.zhouzw.znovel.core.common.resp.RestResp;
import com.zhouzw.znovel.dao.entity.AuthorInfo;
import com.zhouzw.znovel.dao.mapper.AuthorInfoMapper;
import com.zhouzw.znovel.dto.AuthorInfoDto;
import com.zhouzw.znovel.dto.req.AuthorRegisterReqDto;
import com.zhouzw.znovel.manager.cache.AuthorInfoCacheManager;
import com.zhouzw.znovel.service.AuthorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthorServiceImpl implements AuthorService {

    private final AuthorInfoCacheManager authorInfoCacheManager;

    private final AuthorInfoMapper authorInfoMapper;

    // 作家注册
    @Override
    public RestResp<Void> register(AuthorRegisterReqDto dto) {
        // 校验该用户是否已经注册为作家
        AuthorInfoDto author = authorInfoCacheManager.getAuthor(dto.getUserId());

        if(Objects.nonNull(author)) {
            // 该用户已经注册为作家，直接返回
            return RestResp.ok();
        }

        // 保存作家的信息
        AuthorInfo authorInfo = new AuthorInfo();
        authorInfo.setUserId(dto.getUserId());
        authorInfo.setChatAccount(dto.getChatAccount());
        authorInfo.setEmail(dto.getEmail());
        authorInfo.setTelPhone(dto.getTelPhone());
        authorInfo.setInviteCode("0");
        authorInfo.setPenName(dto.getPenName());
        authorInfo.setWorkDirection(dto.getWorkDirection());
        authorInfo.setCreateTime(LocalDateTime.now());
        authorInfo.setUpdateTime(LocalDateTime.now());
        authorInfoMapper.insert(authorInfo);

        // 清除作家缓存
        authorInfoCacheManager.evictAuthorCache();
        return RestResp.ok();
    }

    // 作家状态
    @Override
    public RestResp<Integer> getStatus(Long userId) {
       AuthorInfoDto author = authorInfoCacheManager.getAuthor(userId);
       return Objects.isNull(author) ? RestResp.ok(null) : RestResp.ok(author.getStatus());
    }
}
