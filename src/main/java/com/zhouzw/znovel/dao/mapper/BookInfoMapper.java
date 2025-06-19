package com.zhouzw.znovel.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhouzw.znovel.dao.entity.BookInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BookInfoMapper extends BaseMapper<BookInfo> {

    // 增加小说点击量
    void addVisitCount(@Param("bookId") Long bookId);
}
