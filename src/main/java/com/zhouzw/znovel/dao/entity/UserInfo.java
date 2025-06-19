package com.zhouzw.znovel.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户信息
 */
@TableName("user_info")
@Getter
@Setter
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private String username;

    private String password;

    private String salt;

    private String nickName;

    private String userPhoto;

    private Integer userSex;

    private Long accountBalance;

    /**
     * 用户状态 0-正常
     */
    private Integer status;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", username=" + username +
                ", password=" + password +
                ", salt=" + salt +
                ", nickName=" + nickName +
                ", userPhoto=" + userPhoto +
                ", userSex=" + userSex +
                ", accountBalance=" + accountBalance +
                ", status=" + status +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                "}";
    }

}
