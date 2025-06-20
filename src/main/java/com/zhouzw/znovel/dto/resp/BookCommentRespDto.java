package com.zhouzw.znovel.dto.resp;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zhouzw.znovel.core.json.serializer.UsernameSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


// 小说评论

@Data
@Builder
public class BookCommentRespDto {

    @Schema(description = "评论总数")
    private Long commentTotal;

    @Schema(description = "评论列表")
    private List<CommentInfo> comments;

    @Data
    @Builder
    public static class CommentInfo {

        @Schema(description = "评论ID")
        private Long id;

        @Schema(description = "评论内容")
        private String commentContent;

        @Schema(description = "评论用户")
        @JsonSerialize(using = UsernameSerializer.class)
        private String commentUser;

        @Schema(description = "评论用户ID")
        private Long commentUserId;

        @Schema(description = "评论用户头像")
        private String commentUserPhoto;

        @Schema(description = "评论时间")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime commentTime;

    }
}

