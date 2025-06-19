package com.zhouzw.znovel.dao.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@TableName("user_bookshelf")
@Getter
@Setter
public class UserBookshelf implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private Long userId;

    private Long bookId;

    // 上次阅读的章节内容表ID
    private Long preContentId;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    @Override
    public String toString() {
        return "UserBookshelf{" +
                "id=" + id +
                ", userId=" + userId +
                ", bookId=" + bookId +
                ", preContentId=" + preContentId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                "}";
    }
}
