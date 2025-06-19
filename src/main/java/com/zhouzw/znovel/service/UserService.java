package com.zhouzw.znovel.service;

import com.zhouzw.znovel.core.common.resp.RestResp;
import com.zhouzw.znovel.dto.req.UserInfoUptReqDto;
import com.zhouzw.znovel.dto.req.UserLoginReqDto;
import com.zhouzw.znovel.dto.req.UserRegisterReqDto;
import com.zhouzw.znovel.dto.resp.UserInfoRespDto;
import com.zhouzw.znovel.dto.resp.UserLoginRespDto;
import com.zhouzw.znovel.dto.resp.UserRegisterRespDto;
import jakarta.validation.Valid;

/**
 * 用户模块 服务类
 */
public interface UserService {

    /**
     * 用户注册
     */
    RestResp<UserRegisterRespDto> register(UserRegisterReqDto dto);

    /**
     * 用户登录
     */
    RestResp<UserLoginRespDto> login(@Valid UserLoginReqDto dto);

    /**
     * 用户信息查询
     */
    RestResp<UserInfoRespDto> getUserInfo(Long userId);

    /**
     * 修改用户信息
     */
    RestResp<Void> updateUserInfo(@Valid UserInfoUptReqDto dto);

    // 用户反馈接口
    RestResp<Void> saveFeedback(Long userId,String content);

    // 用户删除反馈接口
    RestResp<Void> deleteFeedback(Long userId, Long id);

    // 查询书架状态
    RestResp<Integer> getBookshelfStatus(Long userId, String bookId);
}
