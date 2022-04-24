package com.wwinfo.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wwinfo.model.Invetorylack;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wwinfo.pojo.query.InvetorylackQuery;
import com.wwinfo.pojo.res.InvetorylackRes;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 资产盘点缺少表 Mapper 接口
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
public interface InvetorylackMapper extends BaseMapper<Invetorylack> {

    IPage page(Page<InvetorylackRes> page, @Param("query") InvetorylackQuery query);

    int addBatch(@Param("lackList") List<Invetorylack> lackList);

    List<Map<String, Object>> getInvetoryInfoByTaskId(@Param("taskID") Long taskID);

}
