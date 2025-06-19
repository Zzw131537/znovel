package com.zhouzw.znovel.dto.resp;

import lombok.Builder;
import lombok.Data;

// 小说分类 - 响应DTO
@Data
@Builder
public class BookCategoryRespDto {

    // 类别ID
    private Long id;

    // 类别名
    private String name;
}
