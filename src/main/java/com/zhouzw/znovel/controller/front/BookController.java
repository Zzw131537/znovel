package com.zhouzw.znovel.controller.front;

// 前台 -小说模块 API 控制器

import com.zhouzw.znovel.core.common.resp.RestResp;
import com.zhouzw.znovel.core.constant.ApiRouterConsts;
import com.zhouzw.znovel.dto.resp.*;
import com.zhouzw.znovel.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Tag(name = "BookController",description = "小说模块")
@RestController
@RequestMapping(ApiRouterConsts.API_FRONT_BOOK_URL_PREFIX)
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    // 小说分类列表查询 0-男频 1-女频
    @GetMapping("/category/list")
    public RestResp<List<BookCategoryRespDto>> listCategory(Integer workDirection) {
        return bookService.listCategory(workDirection);
    }

    // 小说信息查询接口
    @GetMapping("{id}")
    public RestResp<BookInfoRespDto> getBookById(@PathVariable("id") Long bookId) {
        return bookService.getbookById(bookId);
    }

    // 增加小说点击量接口
    @PostMapping("/visit")
    public RestResp<Void> addVisitCount(Long bookId) {
        return bookService.addVisitCount(bookId);
    }

    // 小说最新章节相关信息查询接口
    @GetMapping("/last_chapter/about/{bookId}")
    public RestResp<BookChapterAboutRespDto> getLastChapterAbout(@PathVariable("bookId") Long bookId) {
        return bookService.getLastChapterAbout(bookId);
    }

    // 小说推荐列表查询接口
    @GetMapping("/rec_list")
    public RestResp<List<BookInfoRespDto>> listRecBooks(
            Long bookId
    ) throws NoSuchAlgorithmException {
        return bookService.lisRecBooks(bookId);
    }

    // 小说章节列表查询接口
    @GetMapping("/chapter/list")
    public RestResp<List<BookChapterRespDto>> listChapters(Long bookId) {
        return bookService.listChapters(bookId);
    }

    // 小说内容相关信息查询
    @GetMapping("/content/{chapterId}")
    public RestResp<BookContentAboutRespDto> getBookContentAbout(@PathVariable("chapterId") Long chapterId) {
        return bookService.getBookContentAbout(chapterId);
    }

    // 获取上一章节ID接口
    @GetMapping("/pre_chapter_id/{chapterId}")
    public RestResp<Long> getPreChapterId(@PathVariable("chapterId") Long chapterId) {
        return bookService.getPreChapterId(chapterId);
    }

    // 获取下一章节ID接口
    @GetMapping("next_chapter_id/{chapterId}")
    public RestResp<Long> getNextChapterId(
            @Parameter(description = "章节ID") @PathVariable("chapterId") Long chapterId) {
        return bookService.getNextChapterId(chapterId);
    }

    // 小说点击榜查询接口
    @GetMapping("/visit_rank")
    public RestResp<List<BookRankRespDto>> listVisitRankBooks(){
        return bookService.listVisitRankBooks();
    }

    /**
     * 小说新书榜查询接口
     */
    @GetMapping("newest_rank")
    public RestResp<List<BookRankRespDto>> listNewestRankBooks() {
        return bookService.listNewestRankBooks();
    }

    /**
     * 小说更新榜查询接口
     */
    @GetMapping("update_rank")
    public RestResp<List<BookRankRespDto>> listUpdateRankBooks() {
        return bookService.listUpdateRankBooks();
    }


    /**
     * 小说最新评论查询接口
     */
    @Operation(summary = "小说最新评论查询接口")
    @GetMapping("comment/newest_list")
    public RestResp<BookCommentRespDto> listNewestComments(
            @Parameter(description = "小说ID") Long bookId) {
        return bookService.listNewestComments(bookId);
    }


}



