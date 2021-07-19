package com.group4.secbaselinebackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.group4.secbaselinebackend.models.AvgResponseTime;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author feng xl
 * @date 2021/7/17 0017 19:32
 */
@Mapper
public interface ResTimeMapper extends BaseMapper<AvgResponseTime> {

}
