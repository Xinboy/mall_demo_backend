package com.xinbo.mall_demo.common.api;

import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * 分页数据封装类
 * @author Xinbo
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Page<T> {
    /**
     * 当前页码
     */
    private Integer pageNum;
    /**
     * 每页数量
     */
    private Integer pageSize;
    /**
     * 总页数
     */
    private Integer totalPage;
    /**
     * 总条数
     */
    private Long total;
    /**
     * 分页数据
     */
    private List<T> list;

    /**
     * 将PageHelper分页后的list转为分页信息
     */
    public static <T> Page<T> restPage(List<T> list) {
        Page<T> result = new Page<>();
        PageInfo<T> page = new PageInfo<>(list);
        result.setPageNum(page.getPageNum());
        result.setPageSize(page.getPageSize());
        result.setTotal(page.getTotal());
        result.setTotalPage(page.getPages());
        result.setList(page.getList());
        return result;
    }

    /**
     * 将SpringData分页后的list转为分页信息
     */
    public static <T> Page<T> restPage(PageInfo<T> page) {
        Page<T> result = new Page<>();
        result.setPageNum(page.getPageNum());
        result.setPageSize(page.getPageSize());
        result.setTotal(page.getTotal());
        result.setTotalPage(page.getPages());
        result.setList(page.getList());
        return result;
    }
}

