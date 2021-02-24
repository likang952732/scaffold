package com.xhnj.util;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.xhnj.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Component;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/*
 @Description
 *@author kang.li
 *@date 2021/2/24 10:11   
 */
@Component
@Slf4j
public class FieldRepeatValidatorUtils {
    /**
     * 实体类中id字段
     */
    private String idColumnName;

    /**
     * 实体类中id的值
     */
    private Object idColumnValue;
    /**
     *
     * @param fields 验证的字段数组
     * @param message 如果不满足返回的消息
     * @param o 实体类
     * @return
     */
    public boolean fieldRepeat(String [] fields,String message,Object o){
        try {
            // 没有校验的值返回true
            if(fields != null && fields.length == 0){
                return true;
            }
            checkUpdateOrSave(o);
            checkRepeat(fields,o,message);
            return true;
        }catch (Exception e){
//            String msg = "验证字段是否重复报错";
//            log.error(msg,e);
            throw new BusinessException(e.getMessage());
        }
    }

    /**
     * 通过传入的实体类中 @TableId 注解的值是否为空，来判断是更新还是保存
     * 将值id值和id列名赋值
     * id的值不为空 是更新 否则是插入
     * @param o 被注解修饰过的实体类
     * @return
     */
    public void checkUpdateOrSave(Object o) throws Exception{
        Field[] fields = o.getClass().getDeclaredFields();
        for (Field f:fields) {
            // 设置私有属性可读
            f.setAccessible(true);
            if(f.isAnnotationPresent(TableId.class)){
                TableId tableId = f.getAnnotation(TableId.class);
                idColumnName = tableId.value();
                idColumnValue = f.get(o);
            }
        }
    }

    /**
     * 通过传入的字段值获取数据是否重复
     * @param fields
     * @param o
     * @param message
     * @return
     */
    public void checkRepeat(String [] fields,Object o,String message){
        Model model = (Model) o;
        QueryWrapper queryWrapper = new QueryWrapper();
        Map<String,Object> queryMap = getColumns(fields,o);
        Iterator<Map.Entry<String, Object>> it = queryMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> entry = it.next();
            queryWrapper.eq(entry.getKey(),entry.getValue());
        }
        if(idColumnValue != null){
            //更新的话，那条件就要排除自身
            queryWrapper.eq(idColumnName, idColumnValue);
        }
        List list = model.selectList(queryWrapper);
        if(list != null && list.size()>0){
            throw new BusinessException(message);
        }
    }

    /**
     * 多条件判断唯一性，将我们的属性和值组装在map中，方便后续拼接条件
     * @param fields
     * @param o
     * @return
     */
    public Map<String,Object> getColumns(String [] fields,Object o){
        Field[] fieldList = o.getClass().getDeclaredFields();
        Map<String,Object> map = new HashMap<>();
        for (Field f : fieldList) {
            // ② 设置对象中成员 属性private为可读
            f.setAccessible(true);
            // 判断字段是否包含在数组中，如果存在，则将它对应的列字段放入map中
            if(ArrayUtils.contains(fields,f.getName())){
                getMapData(map,f,o);
            }
        }
        return map;
    }

    /**
     * 得到查询条件
     * @param map  列字段
     * @param f 字段
     * @param o 传入的对象
     */
    private void getMapData( Map<String,Object> map,Field f,Object o){
        try {
            if(f.isAnnotationPresent(TableField.class)){
                TableField tableField = f.getAnnotation(TableField.class);
                Object val = f.get(o);
                map.put(tableField.value(),val);
            }
        }catch (IllegalAccessException i){
            throw new BusinessException("获取字段的值报错");
        }
    }
}
