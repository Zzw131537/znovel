package com.zhouzw.znovel.manager.cache;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhouzw.znovel.core.constant.CacheConsts;
import com.zhouzw.znovel.core.constant.DatabaseConsts;
import com.zhouzw.znovel.dao.entity.AuthorInfo;
import com.zhouzw.znovel.dao.mapper.AuthorInfoMapper;
import com.zhouzw.znovel.dto.AuthorInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Objects;

// 作家信息 缓存管理类

@Component
@RequiredArgsConstructor
public class AuthorInfoCacheManager {

    private final AuthorInfoMapper authorInfoMapper;

    // 查询作家信息并放入缓存
    @Cacheable(cacheManager = CacheConsts.REDIS_CACHE_MANAGER,
    value = CacheConsts.AUTHOR_INFO_CACHE_NAME,unless = "#result == null")
    public AuthorInfoDto getAuthor(Long userId) {
        QueryWrapper<AuthorInfo> queryWrapper = new QueryWrapper<>();

        queryWrapper
                .eq(DatabaseConsts.AuthorInfoTable.COLUMN_USER_ID, userId)
                .last(DatabaseConsts.SqlEnum.LIMIT_1.getSql());

        AuthorInfo authorInfo = authorInfoMapper.selectOne(queryWrapper);

        if(Objects.isNull(authorInfo)) {
            return null;
        }
        return AuthorInfoDto.builder()
                .id(authorInfo.getId())
                .penName(authorInfo.getPenName())
                .status(authorInfo.getStatus())
                .build();
    }

    @CacheEvict(cacheManager = CacheConsts.REDIS_CACHE_MANAGER,value = CacheConsts.AUTHOR_INFO_CACHE_NAME)
    public void evictAuthorCache(){
        // 使用此方法清除作家信息缓存
    }
}
