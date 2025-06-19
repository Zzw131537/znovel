package com.zhouzw.znovel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhouzw.znovel.core.common.constant.CommonConsts;
import com.zhouzw.znovel.core.common.constant.ErrorCodeEnum;
import com.zhouzw.znovel.core.common.exception.BusinessException;
import com.zhouzw.znovel.core.common.req.PageReqDto;
import com.zhouzw.znovel.core.common.resp.PageRespDto;
import com.zhouzw.znovel.core.common.resp.RestResp;
import com.zhouzw.znovel.core.constant.CacheConsts;
import com.zhouzw.znovel.core.constant.DatabaseConsts;
import com.zhouzw.znovel.core.constant.SystemConfigConsts;
import com.zhouzw.znovel.core.utils.JwtUtils;
import com.zhouzw.znovel.dao.entity.UserBookshelf;
import com.zhouzw.znovel.dao.entity.UserFeedback;
import com.zhouzw.znovel.dao.entity.UserInfo;
import com.zhouzw.znovel.dao.mapper.UserBookshelfMapper;
import com.zhouzw.znovel.dao.mapper.UserFeedbackMapper;
import com.zhouzw.znovel.dao.mapper.UserInfoMapper;
import com.zhouzw.znovel.dto.req.UserInfoUptReqDto;
import com.zhouzw.znovel.dto.req.UserLoginReqDto;
import com.zhouzw.znovel.dto.req.UserRegisterReqDto;
import com.zhouzw.znovel.dto.resp.UserCommentRespDto;
import com.zhouzw.znovel.dto.resp.UserInfoRespDto;
import com.zhouzw.znovel.dto.resp.UserLoginRespDto;
import com.zhouzw.znovel.dto.resp.UserRegisterRespDto;
import com.zhouzw.znovel.manager.redis.VerifyCodeManager;
import com.zhouzw.znovel.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.builder.BuilderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserInfoMapper userInfoMapper;

    @Autowired
    private VerifyCodeManager verifyCodeManager;

    private final JwtUtils jwtUtils;

    private final StringRedisTemplate stringRedisTemplate;

    private final UserFeedbackMapper userFeedbackMapper;

    private final UserBookshelfMapper userBookshelfMapper;
    // 用户注册
    @Override
    public RestResp<UserRegisterRespDto> register(UserRegisterReqDto dto) {

        // 注册后端调试使用,假设用户输入的验证码都是对的，在redis中生成相应的缓存
        String key = CacheConsts.IMG_VERIFY_CODE_CACHE_KEY+dto.getSessionId();
        stringRedisTemplate.opsForValue().set(key,dto.getVelCode());
        // 校验图片验证码是否正确
        if(!verifyCodeManager.imgVerifyCodeOk(dto.getSessionId(),dto.getVelCode())) {
            System.out.println("User Register" + verifyCodeManager);
            throw new BusinessException(ErrorCodeEnum.USER_VERIFY_CODE_ERROR);
        }

        // 校验手机号是否已经注册
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq(DatabaseConsts.UserInfoTable.COLUMN_USERNAME,dto.getUsername())
                .last(DatabaseConsts.SqlEnum.LIMIT_1.getSql());
        if(userInfoMapper.selectCount(queryWrapper)>0) {
            // 手机号已注册
            throw new BusinessException(ErrorCodeEnum.USER_NAME_EXIST);
        }

        // 注册成功，保存用户信息
        UserInfo userInfo = new UserInfo();

        userInfo.setPassword(
                DigestUtils.md5DigestAsHex(dto.getPassword().getBytes(StandardCharsets.UTF_8))
        );
        userInfo.setUsername(dto.getUsername());
        userInfo.setNickName(dto.getUsername());
        userInfo.setCreateTime(LocalDateTime.now());
        userInfo.setUpdateTime(LocalDateTime.now());
        userInfo.setSalt("0");
        userInfoMapper.insert(userInfo);

        // 删除校验码
        verifyCodeManager.removeImgVerifyCode(dto.getSessionId());
        // 生成JWT 并返回
        return RestResp.ok(
                UserRegisterRespDto.builder()
                        .token(jwtUtils.generateToken(userInfo.getId(), SystemConfigConsts.NOVEL_FRONT_KEY))
                        .uid(userInfo.getId())
                        .build()
        );
    }


    // 用户登录
    @Override
    public RestResp<UserLoginRespDto> login(UserLoginReqDto dto) {
        // 查询用户信息
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(DatabaseConsts.UserInfoTable.COLUMN_USERNAME,dto.getUsername())
                .last(DatabaseConsts.SqlEnum.LIMIT_1.getSql());

        UserInfo userInfo = userInfoMapper.selectOne(queryWrapper);

        if(Objects.isNull(userInfo)) {
            // 用户不存在
            throw new BusinessException(ErrorCodeEnum.USER_ACCOUNT_NOT_EXIST);
        }

        // 判断密码是否正确
        if(!Objects.equals(userInfo.getPassword(),DigestUtils.md5DigestAsHex(dto.getPassword().getBytes(StandardCharsets.UTF_8)))) {

            // 密码错误
            throw new BusinessException(ErrorCodeEnum.USER_PASSWORD_ERROR);
        }

        // 登录成功,生成JWT 并返回
        return RestResp.ok(UserLoginRespDto.builder()
                .token(jwtUtils.generateToken(userInfo.getId(),SystemConfigConsts.NOVEL_FRONT_KEY))
                .uid(userInfo.getId())
                .nickname(userInfo.getNickName())
                .build());
    }

    // 获取用户信息
    @Override
    public RestResp<UserInfoRespDto> getUserInfo(Long userId) {
        UserInfo userInfo = userInfoMapper.selectById(userId);
        return RestResp.ok(UserInfoRespDto.builder()
                .nickname(userInfo.getNickName())
                .userSex(userInfo.getUserSex())
                .userPhoto(userInfo.getUserPhoto())
                .build());
    }

    // 修改用户信息
    @Override
    public RestResp<Void> updateUserInfo(UserInfoUptReqDto dto) {
        UserInfo userInfo= new UserInfo();

        userInfo.setId(dto.getUserId());
        userInfo.setNickName(dto.getNickName());
        userInfo.setUserPhoto(dto.getUserPhoto());
        userInfo.setUserSex(dto.getUserSex());
        userInfoMapper.updateById(userInfo);
        return RestResp.ok();
    }

    // 用户反馈接口
    @Override
    public RestResp<Void> saveFeedback(Long userId,String content) {
        UserFeedback userFeedback = new UserFeedback();

        userFeedback.setUserId(userId);
        userFeedback.setContent(content);
        userFeedback.setCreateTime(LocalDateTime.now());
        userFeedback.setUpdateTime(LocalDateTime.now());
        userFeedbackMapper.insert(userFeedback);
        return RestResp.ok();
    }

    // 用户删除反馈接口
    @Override
    public RestResp<Void> deleteFeedback(Long userId, Long id) {
        QueryWrapper<UserFeedback> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(DatabaseConsts.CommonColumEnum.ID.getName(),id)
                .eq(DatabaseConsts.UserFeedBackTable.COLUMN_USER_ID,userId);
        userFeedbackMapper.delete(queryWrapper);
        return RestResp.ok();
    }

    @Override
    public RestResp<Integer> getBookshelfStatus(Long userId, String bookId) {
        QueryWrapper<UserBookshelf> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq(DatabaseConsts.UserBookshelfTable.COLUMN_USER_ID,userId)
                .eq(DatabaseConsts.UserBookshelfTable.COLUMN_BOOK_ID,bookId);

        return RestResp.ok(
                userBookshelfMapper.selectCount(queryWrapper) > 0
                ? CommonConsts.YES : CommonConsts.NO
        );
    }



}
