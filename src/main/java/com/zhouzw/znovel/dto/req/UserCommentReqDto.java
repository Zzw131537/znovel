package com.zhouzw.znovel.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
public class UserCommentReqDto {

    private Long userId;

    @NotNull(message="小说ID不能为空！")
    private Long bookId;

    @Schema(description = "评论内容", required = true)
    @NotBlank(message="评论不能为空！")
    @Length(min = 10,max = 512)
    private String commentContent;
}
