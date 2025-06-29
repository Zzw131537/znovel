package com.zhouzw.znovel.manager.redis;

import java.io.IOException;
import java.time.Duration;
import java.util.Objects;

import com.zhouzw.znovel.core.common.utils.ImgVerifyCodeUtils;
import com.zhouzw.znovel.core.constant.CacheConsts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 验证码 管理类
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class VerifyCodeManager {

    private final StringRedisTemplate stringRedisTemplate;

    /**
     * 生成图形验证码，并放入 Redis 中
     */
    public String genImgVerifyCode(String sessionId) throws IOException {
        String verifyCode = ImgVerifyCodeUtils.getRandVerifyCode(4);
        String img = ImgVerifyCodeUtils.genVerifyCodeImg(verifyCode);
        stringRedisTemplate.opsForValue().set(CacheConsts.IMG_VERIFY_CODE_CACHE_KEY + sessionId,
                verifyCode, Duration.ofMinutes(5));
        return img;
    }

    /**
     * 校验图形验证码
     */
    public boolean imgVerifyCodeOk(String sessionId, String verifyCode) {
       // System.out.println(stringRedisTemplate.opsForValue().get(CacheConsts.IMG_VERIFY_CODE_CACHE_KEY + sessionId));
        //System.out.println(verifyCode);
        return Objects.equals(stringRedisTemplate.opsForValue()
                .get(CacheConsts.IMG_VERIFY_CODE_CACHE_KEY + sessionId), verifyCode);

    }

    /**
     * 从 Redis 中删除验证码
     */
    public void removeImgVerifyCode(String sessionId) {
        stringRedisTemplate.delete(CacheConsts.IMG_VERIFY_CODE_CACHE_KEY + sessionId);
    }

}
