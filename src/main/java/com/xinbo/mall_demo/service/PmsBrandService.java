package com.xinbo.mall_demo.service;


import com.xinbo.mall_demo.mbg.model.PmsBrand;

import java.util.List;

/**
 * PmsBrandService
 * @author Xinbo
 */
public interface PmsBrandService {
    /**
     * 获取所有的品牌
     * @return 品牌列表
     */
    List<PmsBrand> listAll();

    /**
     * 分页获取品牌列表
     * @param pageNum 分页序号
     * @param pageSize 分页大小
     * @return 品牌列表
     */
    List<PmsBrand> list(int pageNum, int pageSize);

    /**
     * 获取指定品牌
     * @param id 品牌id
     * @return 指定品牌
     */
    PmsBrand get(Long id);

    /**
     * 获取指定品牌
     * @param brand 品牌内容
     * @return 新建结果
     */
    int create(PmsBrand brand);

    /**
     * 获取指定品牌
     * @param id 品牌id
     * @param brand 更新后的品牌参数
     * @return 更新结果
     */
    int update(Long id, PmsBrand brand);

    /**
     * 获取指定品牌
     * @param id 品牌id
     * @return 删除结果
     */
    int delete(Long id);

}
