package com.xinbo.mall_demo.service;


import com.xinbo.mall_demo.model.elasticsearch.EsProduct;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EsProductService {
    /**
     * 从数据库中导入所有商品到ES
     */
    int importAll();

    /**
     * 根据id删除商品
     */
    void delete(Long id);

    /**
     * 批量删除商品
     */
    void delete(List<Long> ids);

    /**
     * 根据id创建商品
     */
    EsProduct create(Long id);

    /**
     * 根据关键字搜索名称或者副标题
     */
    Page<EsProduct> search(String keyword, Integer pagenum, Integer pageSize);
}
