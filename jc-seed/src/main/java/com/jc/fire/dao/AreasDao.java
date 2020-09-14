package com.jc.fire.dao;

import java.util.List;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.jc.fire.model.Areas;
import com.jc.fire.model.request.PageRequest;

@DS("waterdb")
public interface AreasDao {
    int deleteByPrimaryKey(Long id);

    int insert(Areas record);

    int insertSelective(Areas record);

    Areas selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Areas record);

    int updateByPrimaryKey(Areas record);

    List<Areas> selectAreas(PageRequest pageRequest);
}