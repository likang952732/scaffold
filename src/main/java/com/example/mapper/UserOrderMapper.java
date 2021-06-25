package com.example.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.dto.UserOrderParam;
import com.example.model.UserOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lk
 * @since 2021-06-25
 */
public interface UserOrderMapper extends BaseMapper<UserOrder> {
    IPage<UserOrder> listPage(IPage<UserOrder> page, @Param("userOrderParam") UserOrderParam userOrderParam);
}
