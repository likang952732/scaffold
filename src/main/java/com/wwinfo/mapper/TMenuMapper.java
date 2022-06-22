package com.wwinfo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wwinfo.model.TMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author lk
 * @since 2021-02-24
 */
public interface TMenuMapper extends BaseMapper<TMenu> {

    TMenu getMenuByName(@Param("name")String name);

    List<TMenu> getUmsMenu(@Param("roleId") Long roleId);

    List<TMenu> getMenuByUserId(@Param("userId") Long userId);

    List<TMenu> selectMenuList(@Param("name") String keyword,@Param("parent_id") String parentId,Integer pageSize, Integer pageNum);

}
