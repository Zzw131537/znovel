package com.zhouzw.znovel.dao.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 小说评论
 */
@TableName("book_comment")
@Getter
@Setter
public class BookComment implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private Long bookId;

    private Long userId;

    private String commentContent;

    private Integer replyCount;

    // 审核状态 0-待审核 1-审核通过 2-审核不通过
    private Integer auditStatus;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    @Override
    public String toString() {
        return "BookComment{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", userId=" + userId +
                ", commentContent=" + commentContent +
                ", replyCount=" + replyCount +
                ", auditStatus=" + auditStatus +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                "}";
    }
}
