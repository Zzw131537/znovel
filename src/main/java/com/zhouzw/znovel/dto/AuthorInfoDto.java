package com.zhouzw.znovel.dto;


import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class AuthorInfoDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String penName;

    private Integer status;
}
