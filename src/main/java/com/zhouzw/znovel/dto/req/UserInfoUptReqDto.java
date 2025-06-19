package com.zhouzw.znovel.dto.req;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
public class UserInfoUptReqDto {

    private Long userId;

    @Length(min=2,max=10)
    private String nickName;

    @Schema(description = "头像地址")
    @Pattern(regexp="^/[^\s]{10,}\\.(png|PNG|jpg|JPG|jpeg|JPEG|gif|GIF|bpm|BPM)$")
    private String userPhoto;

    @Min(value = 0)
    @Max(value = 1)
    private Integer userSex;

}
