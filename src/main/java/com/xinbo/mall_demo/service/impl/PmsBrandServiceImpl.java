package com.xinbo.mall_demo.service.impl;

import com.github.pagehelper.PageHelper;
import com.xinbo.mall_demo.mbg.mapper.PmsBrandMapper;
import com.xinbo.mall_demo.mbg.model.PmsBrand;
import com.xinbo.mall_demo.mbg.model.PmsBrandExample;
import com.xinbo.mall_demo.service.PmsBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Xinbo
 */
@Service
public class PmsBrandServiceImpl implements PmsBrandService {

    @Autowired
    private PmsBrandMapper brandMapper;

    @Override
    public List<PmsBrand> listAll() {
        return brandMapper.selectByExample(new PmsBrandExample());
    }

    @Override
    public List<PmsBrand> list(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        brandMapper.selectByExample(new PmsBrandExample());
        return brandMapper.selectByExample(new PmsBrandExample());
    }

    @Override
    public PmsBrand get(Long id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    @Override
    public int create(PmsBrand brand) {
        return brandMapper.insertSelective(brand);
    }

    @Override
    public int update(Long id, PmsBrand brand) {
        brand.setId(id);
        return brandMapper.updateByPrimaryKeySelective(brand);
    }

    @Override
    public int delete(Long id) {
        return brandMapper.deleteByPrimaryKey(id);
    }
}
