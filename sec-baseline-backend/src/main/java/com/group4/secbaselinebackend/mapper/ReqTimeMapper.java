package com.group4.secbaselinebackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.group4.secbaselinebackend.models.AvgRequestTime;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author feng xl
 * @date 2021/7/17 0017 19:19
 */

@Mapper
public interface ReqTimeMapper extends BaseMapper<AvgRequestTime> {
}
