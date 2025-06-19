package com.zhouzw.znovel.dto.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class ChapterUpdateReqDto  {

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
