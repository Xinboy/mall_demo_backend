package com.xinbo.mall_demo.mbg.mapper;

import com.xinbo.mall_demo.mbg.model.PmsProductAttributeCategory;
import com.xinbo.mall_demo.mbg.model.PmsProductAttributeCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PmsProductAttributeCategoryMapper {
    long countByExample(PmsProductAttributeCategoryExample example);

    int deleteByExample(PmsProductAttributeCategoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PmsProductAttributeCategory row);

    int insertSelective(PmsProductAttributeCategory row);

    List<PmsProductAttributeCategory> selectByExample(PmsProductAttributeCategoryExample example);

    PmsProductAttributeCategory selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") PmsProductAttributeCategory row, @Param("example") PmsProductAttributeCategoryExample example);

    int updateByExample(@Param("row") PmsProductAttributeCategory row, @Param("example") PmsProductAttributeCategoryExample example);

    int updateByPrimaryKeySelective(PmsProductAttributeCategory row);

    int updateByPrimaryKey(PmsProductAttributeCategory row);
}