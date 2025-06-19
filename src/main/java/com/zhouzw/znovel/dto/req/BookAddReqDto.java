package com.zhouzw.znovel.dto.req;

// 小说发布请求DTO

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class BookAddReqDto {

    // 作品方向
    @NotNull
    private Integer workDirection;

    // 类别Id
    @NotNull
    private Long categoryId;

    // 类别名
    @NotBlank
    private String categoryName;

    // 小说封面地址
    @NotBlank
    private String picUrl;

    // 小说名
    @NotBlank
    private String bookName;

    // 书籍描述
    @NotBlank
    private String bookDesc;

    // 是否收费
    @NotNull
    private Integer isVip;
}
