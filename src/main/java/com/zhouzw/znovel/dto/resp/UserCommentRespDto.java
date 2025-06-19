package com.zhouzw.znovel.dto.resp;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserCommentRespDto {

    @Schema(description = "评论内容")
    private String commentContent;

    @Schema(description = "评论小说封面")
    private String commentBookPic;

    @Schema(description = "评论小说")
    private String commentBook;

    @Schema(description = "评论时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime commentTime;


}
