package com.zhouzw.znovel.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhouzw.znovel.dao.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.jboss.marshalling.TraceInformation;
import org.springframework.stereotype.Repository;

/**
 * 用户信息Mapper接口
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {
}
