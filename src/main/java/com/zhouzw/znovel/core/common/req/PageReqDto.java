package com.zhouzw.znovel.core.common.req;


import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

@Data
public class PageReqDto {

    /**
     * 请求页码，默认第 1 页
     */
    @Parameter(description = "请求页码，默认第 1 页")
    private int pageNum = 1;

    /**
     * 每页大小，默认每页 10 条
     */
    @Parameter(description = "每页大小，默认每页 10 条")
    private int pageSize = 10;

    /**
     * 是否查询所有，默认不查所有 为 true 时，pageNum 和 pageSize 无效
     */
    @Parameter(hidden = true)
    private boolean fetchAll = false;
}
