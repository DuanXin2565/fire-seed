package com.jc.fire.dao;

import java.util.List;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.jc.fire.model.Area;

@DS("testDb")
public interface AreaDao {
    int deleteByPrimaryKey(Long areaId);

    int insert(Area record);

    int insertSelective(Area record);

    Area selectByPrimaryKey(Long areaId);

    int updateByPrimaryKeySelective(Area record);

    int updateByPrimaryKey(Area record);

    int batchInsert(List<Area> areas);
}