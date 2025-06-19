package com.zhouzw.znovel.controller.front;

import com.zhouzw.znovel.core.auth.UserHolder;
import com.zhouzw.znovel.core.common.req.PageReqDto;
import com.zhouzw.znovel.core.common.resp.PageRespDto;
import com.zhouzw.znovel.core.common.resp.RestResp;
import com.zhouzw.znovel.core.constant.ApiRouterConsts;
import com.zhouzw.znovel.core.constant.SystemConfigConsts;
import com.zhouzw.znovel.dto.req.UserCommentReqDto;
import com.zhouzw.znovel.dto.req.UserInfoUptReqDto;
import com.zhouzw.znovel.dto.req.UserLoginReqDto;
import com.zhouzw.znovel.dto.req.UserRegisterReqDto;
import com.zhouzw.znovel.dto.resp.UserCommentRespDto;
import com.zhouzw.znovel.dto.resp.UserInfoRespDto;
import com.zhouzw.znovel.dto.resp.UserLoginRespDto;
import com.zhouzw.znovel.dto.resp.UserRegisterRespDto;
import com.zhouzw.znovel.service.BookService;
import com.zhouzw.znovel.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

/**
 * 前台门户 - 用户 API 控制器
 */
@Tag(name = "UserController",description = "前台门户-用户模块")
@SecurityRequirement(name = SystemConfigConsts.HTTP_AUTH_HEADER_NAME)
@RestController
@RequestMapping(ApiRouterConsts.API_FRONT_USER_URL_PREFIX)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final BookService bookService;

    /**
     * 用户注册接口
     */
    @Operation(summary = "用户注册接口")
    @PostMapping("/register")
    public RestResp<UserRegisterRespDto> register(@Valid @ModelAttribute @RequestBody UserRegisterReqDto dto) {
        return userService.register(dto);
    }

    /**
     * 用户登录接口
     */
    @Operation(summary = "用户登录接口")
    @PostMapping("/login")
    public RestResp<UserLoginRespDto> login(@Valid @ModelAttribute @RequestBody UserLoginReqDto dto) {
        return userService.login(dto);
    }

    /**
     * 用户信息查询接口
     */
    @Operation(summary = "用户信息查询接口")
    @GetMapping
    public RestResp<UserInfoRespDto>  getUserInfo() {
        System.out.println("当前要查询的用户ID 为: "+UserHolder.getUserId());
        return userService.getUserInfo(UserHolder.getUserId());
    }

    /**
     * 用户信息修改接口
     */
    @Operation(summary = "用户信息修改接口")
    @PutMapping
    public RestResp<Void> updateUserInfo(@Valid @ModelAttribute @RequestBody UserInfoUptReqDto dto) {
        dto.setUserId(UserHolder.getUserId());

        return userService.updateUserInfo(dto);

    }

    /**
     * 用户反馈提交接口
     */
    @Operation(summary = "用户反馈提交接口")
    @PostMapping("/feedback")
    public RestResp<Void> submitFeedback(@Valid @ModelAttribute @RequestBody String content) {
        return userService.saveFeedback(UserHolder.getUserId(),content);
    }

    /**
     * 用户反馈删除接口
     */
    @Operation(summary = "用户反馈删除接口")
    @DeleteMapping("/feedback/{id}")
    public RestResp<Void>deleteFeedback(@PathVariable(name = "id") @Parameter(description = "反馈ID") Long id) {
        return userService.deleteFeedback(UserHolder.getUserId(),id);
    }

    /**
     * 发表评论接口
     */
    @Operation(summary = "发表评论接口")
    @PostMapping("/comment")
    public RestResp<Void> commont(@Valid @ModelAttribute @RequestBody UserCommentReqDto dto) {
        dto.setUserId(UserHolder.getUserId());
        return bookService.saveComment(dto);
    }

    /**
     * 修改评论接口
     */
    @Operation(summary = "修改评论接口")
    @PutMapping("/comment/{id}")
    public RestResp<Void> updateComment(@Parameter(description = "评论ID") @PathVariable(name = "id") Long id,String content) {
        return bookService.updateComment(UserHolder.getUserId(),id,content);
    }

    /**
     * 删除评论接口
     */
    @Operation(summary = "删除评论接口")
    @DeleteMapping("/comment/{id}")
    public RestResp<Void> deleteComment(@Parameter(description = "评论ID") @PathVariable(name = "id") Long id) {
        return bookService.deleteComment(UserHolder.getUserId(),id);
    }

    // 查询书是否在该用户的书架上 0-不在 1-在
    @Operation(summary = "查询书架状态接口")
    @GetMapping("/bookshelf_status")
    public RestResp<Integer> getBookshelfStatus(@Parameter(description = "小说ID") String bookId) {
        return userService.getBookshelfStatus(UserHolder.getUserId(),bookId);
    }

    /**
     * 分页查询评论
     */
    @Operation(summary = "查询用户评论接口")
    @GetMapping("/comments")
    public RestResp<PageRespDto<UserCommentRespDto>> listComments(PageReqDto pageReqDto) {
        return bookService.listComments(UserHolder.getUserId(),pageReqDto);
    }

}
