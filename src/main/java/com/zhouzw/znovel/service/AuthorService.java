package com.zhouzw.znovel.service;

import com.zhouzw.znovel.core.common.resp.RestResp;
import com.zhouzw.znovel.dto.req.AuthorRegisterReqDto;
import jakarta.validation.Valid;

// 作家模块 业务服务类
public interface AuthorService {

    // 作家注册
    RestResp<Void> register(@Valid AuthorRegisterReqDto dto);

    // 查询作家状态
    RestResp<Integer> getStatus(Long userId);
}
