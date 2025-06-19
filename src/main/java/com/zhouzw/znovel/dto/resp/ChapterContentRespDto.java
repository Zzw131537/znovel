package com.zhouzw.znovel.dto.resp;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChapterContentRespDto {

    // 章节标题
    private String chapterName;

    // 章节内容
    private String chapterContent;

    // 是否收费
    private Integer isVip;
}
