package com.zhouzw.znovel.dto.resp;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookChapterAboutRespDto {

    private BookChapterRespDto chapterInfo;

    // 章节总数
    private Long chapterTotal;

    // 内容概要
    private String contentSummary;
}
