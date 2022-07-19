package com.xinbo.mall_demo.model.dao;

import com.xinbo.mall_demo.model.elasticsearch.EsProduct;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

public interface EsProductDao {
    List<EsProduct> getAllEsProductList(@Param("id") Long id);
}
