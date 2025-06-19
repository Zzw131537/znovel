package com.zhouzw.znovel.dao.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@TableName("user_feedback")
@Setter
@Getter
public class UserFeedback implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private Long userId;

    private String content;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    @Override
    public String toString() {
        return "UserFeedback{" +
                "id=" + id +
                ", userId=" + userId +
                ", content=" + content +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                "}";
    }
}
