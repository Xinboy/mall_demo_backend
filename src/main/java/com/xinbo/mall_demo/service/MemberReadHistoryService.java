package com.xinbo.mall_demo.service;

import com.xinbo.mall_demo.model.mongodb.document.MemberReadHistory;

import java.util.List;

/**
 * 会员浏览记录管理接口
 * @author xinbo
 */
public interface MemberReadHistoryService {

    /**
     * 生成浏览记录
     * @param memberReadHistory 传入对象
     * @return 生成结果
     */
    int create(MemberReadHistory memberReadHistory);

    /**
     * 批量删除浏览记录
     * @param ids id列表
     * @return 删除结果
     */
    int delete(List<String> ids);

    /**
     * 获取用户浏览历史记录
     * @param memberId 会员id
     * @return List 浏览记录数组
     */
    List<MemberReadHistory> list(Long memberId);


    /**
     * 清除指定用户的所有历史记录
     * @param memberId 会员 id
     * @return int 清除结果
     */
    int clear(Long memberId);

    /**
     * 清除所有历史记录
     * @return 删除结果
     */
    int clear();

}
