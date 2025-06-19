package com.zhouzw.znovel.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;

@TableName("book_content")
@Data
public class BookContent implements Serializable {
  private static final long serialVersionUID = 1L;

  // 主键
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    // 章节id
    private Long chapterId;

    // 小说内容
    private String content;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
