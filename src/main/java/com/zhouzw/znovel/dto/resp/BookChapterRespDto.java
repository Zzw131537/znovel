package com.zhouzw.znovel.dto.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
public class BookChapterRespDto implements Serializable {

    private static final long serialVersionUID = 1L;

    // 章节Id
    private Long id;

    // 小说id
    private Long bookId;

    // 章节号
    private Integer chapterNum;

    // 章节ming
    private String chapterName;

    // 章节字数
    private Integer chapterWordCount;

    // 更新时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime chapterUpdateTime;

    // 是否收费
    private Integer isVip;
}
