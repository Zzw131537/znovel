package com.zhouzw.znovel.dao.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

@TableName("book_chapter")
public class BookChapter {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    // 小说ID
    private Long bookId;

    // 章节号
    private Integer chapterNum;

    // 章节名
    private String chapterName;

    // 章节字数
    private Integer wordCount;

    //是否收费
    private Integer isVip;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Integer getChapterNum() {
        return chapterNum;
    }

    public void setChapterNum(Integer chapterNum) {
        this.chapterNum = chapterNum;
    }

    public Integer getWordCount() {
        return wordCount;
    }

    public void setWordCount(Integer wordCount) {
        this.wordCount = wordCount;
    }

    public Integer getIsVip() {
        return isVip;
    }

    public void setIsVip(Integer isVip) {
        this.isVip = isVip;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "BookChapter{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", chapterNum=" + chapterNum +
                ", chapterName='" + chapterName + '\'' +
                ", wordCount=" + wordCount +
                ", isVip=" + isVip +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
