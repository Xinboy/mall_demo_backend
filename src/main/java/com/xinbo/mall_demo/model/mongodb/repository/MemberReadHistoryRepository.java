package com.xinbo.mall_demo.model.mongodb.repository;

import com.xinbo.mall_demo.model.mongodb.document.MemberReadHistory;
import org.elasticsearch.common.recycler.Recycler;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * 会员商品浏览历史Repository
 */
public interface MemberReadHistoryRepository extends MongoRepository<MemberReadHistory, String> {
    /**
     * 根据会员id安时间倒叙获取浏览记录
     * @param memberId 会员id
     **/
    List<MemberReadHistory> findByMemberIdOrderByCreateTimeDesc(Long memberId);
}
