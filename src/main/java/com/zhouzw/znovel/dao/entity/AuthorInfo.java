package com.zhouzw.znovel.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

// 作家信息
@TableName("author_info")
@Getter
@Setter
public class AuthorInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    // 用户id
    private Long userId;

    // 笔名
    private String penName;

    // 手机号码
    private String telPhone;

    // 邀请码
    private String  inviteCode;

    // 社交账号
    private String chatAccount;

    // 电子邮箱
    private String email;

    // 作家状态 0-正常 1-封禁
    private Integer status;

    // 作品方向 0-男频 1-女频
    private Integer workDirection;

    // 创建时间
    private LocalDateTime createTime;

    // 更新时间
    private LocalDateTime updateTime;

    @Override
    public String toString() {
        return "AuthorInfo{" +
                "id=" + id +
                ", userId=" + userId +
                ", penName='" + penName + '\'' +
                ", telPhone='" + telPhone + '\'' +
                ", chatAccount='" + chatAccount + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                ", workDirection=" + workDirection +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
