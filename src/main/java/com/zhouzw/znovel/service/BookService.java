package com.zhouzw.znovel.service;

import com.zhouzw.znovel.core.common.req.PageReqDto;
import com.zhouzw.znovel.core.common.resp.PageRespDto;
import com.zhouzw.znovel.core.common.resp.RestResp;
import com.zhouzw.znovel.dto.req.BookAddReqDto;
import com.zhouzw.znovel.dto.req.ChapterAddReqDto;
import com.zhouzw.znovel.dto.req.ChapterUpdateReqDto;
import com.zhouzw.znovel.dto.req.UserCommentReqDto;
import com.zhouzw.znovel.dto.resp.*;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface BookService {

    // 发表评论接口
    RestResp<Void> saveComment(@Valid UserCommentReqDto dto);

    // 修改评论接口
    RestResp<Void> updateComment(Long userId, Long id, String content);

    // 删除评论接口
    RestResp<Void> deleteComment(Long userId, Long id);

    // 分页查询该用户的评论
    RestResp<PageRespDto<UserCommentRespDto>> listComments(Long userId, PageReqDto pageReqDto);

    // 添加小说
    RestResp<Void> saveBook(@Valid BookAddReqDto dto);

    // 列出作家小说列表
    RestResp<PageRespDto<BookInfoRespDto>> listAuthorBooks(PageReqDto dto);

    // 添加章节
    RestResp<Void> saveBookChapter(@Valid ChapterAddReqDto dto);

    // 删除小说章节
    RestResp<Void> deleteBookChapter(Long chapterId);

    // 小说章节查询
    RestResp<ChapterContentRespDto> getBookChapter(Long chapterId);

    // 小说章节更新
    RestResp<Void> updateBookChapter(Long chapterId, @Valid ChapterUpdateReqDto dto);

    // 小说章节发布列表接口
    RestResp<PageRespDto<BookChapterRespDto>> listBookChapters(Long bookId, PageReqDto dto);

    // 小说分类列表查询
    RestResp<List<BookCategoryRespDto>> listCategory(Integer workDirection);

    // 查询小说信息
    RestResp<BookInfoRespDto> getbookById(Long bookId);

    // 增加小说点击量
    RestResp<Void> addVisitCount(Long bookId);

    // 小说最新章节相关信息
    RestResp<BookChapterAboutRespDto> getLastChapterAbout(Long bookId);

    // 小说推荐列表
    RestResp<List<BookInfoRespDto>> lisRecBooks(Long bookId) throws NoSuchAlgorithmException;


    // 查询小说章节
    RestResp<List<BookChapterRespDto>> listChapters(Long bookId);

    //小说内容相关
    RestResp<BookContentAboutRespDto> getBookContentAbout(Long chapterId);

    // 获取上一章节ID
    RestResp<Long> getPreChapterId(Long chapterId);

    // 获取下一章节ID
    RestResp<Long> getNextChapterId(Long chapterId);

    // 小说点击量排行榜
    RestResp<List<BookRankRespDto>> listVisitRankBooks();

    // 小说新书榜
    RestResp<List<BookRankRespDto>> listNewestRankBooks();

    // 小说更新榜
    RestResp<List<BookRankRespDto>> listUpdateRankBooks();

    RestResp<BookCommentRespDto> listNewestComments(Long bookId);
}
