package com.zhouzw.znovel.dto.req;


// 章节发布 请求DTO

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
public class ChapterAddReqDto {

    // 小说Id
    private Long bookId;

    // 章节名
    @NotBlank
    private String chapterName;

    // 章节内容
    @NotBlank
    @Length(min = 5)
    private String chapterContent;

    // 是否收费
    @NotNull
    private Integer isVip;
}
