package com.yangxin.demo.mapper;


import com.yangxin.demo.bean.BaseModel;
import com.yangxin.demo.bean.Condition;
import com.yangxin.demo.bean.Sort;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author fengjianshe 基本数据库操作统一mapper
 */
public interface BaseMapper<T extends BaseModel> {

    int deleteByPrimaryKey(Integer id);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);

    List<T> getByConditions(@Param("conditions") Map<String, Object> conditions);

    List<T> getByConditionList(@Param("conditions") List<Condition> conditions);

    List<T> getSortedResultByConditionList(@Param("conditions") List<Condition> conditions,
                                           @Param("sorter") Sort sort);

    List<T> getByIn(@Param("field") String field, @Param("set") Set<Object> set);

}
